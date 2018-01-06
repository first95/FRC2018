package org.usfirst.frc.team95.robot.components.test;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;
import org.usfirst.frc.team95.robot.components.DrivePod;

public class AdjustedTalonTester extends JFrame implements TestCanSpeedController.Listener {
	private static final long serialVersionUID = -8288607681504299432L;
	
	AdjustedTalon uut;
	TestPowerDistributionPanel pdp = new TestPowerDistributionPanel();
	private double  lastCommandedThrottle;
	private double  lastCommandedSpeed;
	
	public AdjustedTalonTester(String title) {
		super(title);
		
		uut = new AdjustedTalon(new TestCanSpeedController(0, this), pdp);
		
	}

	public void runThrottleTest() {
		// Create dataset
		XYDataset dataset = new XYSeriesCollection();
		
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        int seriesNum = 0;
		for(double voltage = 10; voltage >= 5.0; voltage -= 0.5) {
			pdp.setVoltage(voltage);
			XYSeries series = new XYSeries("Battery at " + pdp.getVoltage() + "V", false);
			
			for(double throttle = -1.0; throttle <= 1.0; throttle += 0.05) {
				uut.set(throttle);
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
			for(int i = 0; i < AdjustedTalon.BACKWARDS_MULTIPLIER; ++i) {
				pdp.setVoltage(13);
				uut.set(0.0);
			}
			
			XYSeries series = new XYSeries("Throttle at " + throttle, false);
			for(; voltage >= 5.0; voltage -= 0.05) {
				pdp.setVoltage(voltage);
				uut.set(throttle);
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AdjustedTalonTester example1 = new AdjustedTalonTester("Adjusted Talon test");
			example1.runThrottleTest();
			example1.setSize(1000, 1000);
			example1.setLocationRelativeTo(null);
			example1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example1.setVisible(true);

			AdjustedTalonTester example2 = new AdjustedTalonTester("Adjusted Talon test");
			example2.runVoltageTest();
			example2.setSize(1000, 1000);
			example2.setLocationRelativeTo(null);
			example2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example2.setVisible(true);
		
		});
	}

	
	// Methods for TestCanSpeedController.Listener
	@Override
	public void takeSet(int id, double set) {
		lastCommandedThrottle = set;
	}

	@Override
	public void takeSpeed(int id, double speed) {
		lastCommandedSpeed = speed;
		
	}

	@Override
	public void takePosition(int id, double pos) {
		// TODO Auto-generated method stub
		
	}

}
