package tests;

import java.awt.Color;

import javax.swing.JFrame;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;

import mocks.MockMotorController;
import mocks.MockPowerDistributionPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.ctre.phoenix.motorcontrol.ControlMode;


public class AdjustedTalonTester extends JFrame implements MockMotorController.Listener {
	private static final long serialVersionUID = -8288607681504299432L;
	
	private AdjustedTalon uut;
	private MockPowerDistributionPanel pdp = new MockPowerDistributionPanel();
	private double  lastCommandedThrottle;
	private double  lastCommandedSpeed;
	
	public AdjustedTalonTester(String title) {
		super(title);
		
		uut = new AdjustedTalon(new MockMotorController(this), pdp);
		
	}

	public void runThrottleTest() {
		// Create dataset
		XYDataset dataset = new XYSeriesCollection();
		
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        int seriesNum = 0;
		for(double voltage = 10; voltage >= 5.0; voltage -= 0.5) {
			pdp.setVoltage(voltage);
			XYSeries series = new XYSeries("Battery at " + pdp.getVoltage() + "V", false);
			
			for(double throttle = -1.0; throttle <= 1.0; throttle += 0.01) {
				uut.set(ControlMode.PercentOutput, throttle);
				series.add(throttle, lastCommandedThrottle);
			}
			((XYSeriesCollection)dataset).addSeries(series);			
			renderer.setSeriesLinesVisible(seriesNum, true);
	        renderer.setSeriesShapesVisible(seriesNum, false);
	        seriesNum++;
		}

		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Adjusted throttle vs input", "Input throttle", "Adjusted throttle",
				dataset);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(200, 200, 200));
		plot.setRenderer(renderer);

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	public void runVoltageTest() {
		// Create dataset
		XYDataset dataset = new XYSeriesCollection();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        int seriesNum = 0;
		for(double throttle = -1.0; throttle <= 1.0; throttle += 0.25) {
			// Prime the UUT with normal voltages for a while
			double voltage = 13;
			for(int i = 0; i < AdjustedTalon.NUM_RECENT_SAMPLES; ++i) {
				pdp.setVoltage(13);
				uut.set(ControlMode.PercentOutput, 0.0);
			}
			
			XYSeries series = new XYSeries("Throttle at " + throttle, false);
			for(; voltage >= 5.0; voltage -= 0.01) {
				pdp.setVoltage(voltage);
				uut.set(ControlMode.PercentOutput, throttle);
				series.add(voltage, lastCommandedThrottle);
			}
			((XYSeriesCollection)dataset).addSeries(series);			

			renderer.setSeriesLinesVisible(seriesNum, true);
	        renderer.setSeriesShapesVisible(seriesNum, false);
	        seriesNum++;
		}

		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Adjusted throttle vs battery voltage", "voltage", "Adjusted throttle",
				dataset);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(200, 200, 200));
        plot.setRenderer(renderer);

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	// Methods for TestCanSpeedController.Listener
	@Override
	public void takeSet(MockMotorController source, double set) {
		lastCommandedThrottle = set;
	}

	@Override
	public void takeSpeed(MockMotorController source, double speed) {
		lastCommandedSpeed = speed;
		
	}

	@Override
	public void takePosition(MockMotorController source, double pos) {
		// TODO Auto-generated method stub
		
	}

}
