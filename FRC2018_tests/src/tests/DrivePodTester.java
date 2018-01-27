package tests;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;
import org.usfirst.frc.team95.robot.components.DrivePod;

import mocks.MockMotorController;
import mocks.MockSolenoid;

public class DrivePodTester extends JFrame implements MockSolenoid.Listener, MockMotorController.Listener {
	private static final long serialVersionUID = 6749988287158526368L;
	
	private DrivePod uut;
	private MockMotorController leader, follower1, follower2;
	private MockSolenoid shifter;
	
	private double lastLeaderThrottle;
	private boolean lastShifterGear;
	
	public DrivePodTester(String title) {
		super(title);
		leader = new MockMotorController(this);
		follower1 = new MockMotorController(this);
		follower2 = new MockMotorController(this);
		shifter = new MockSolenoid(this);
		
		uut = new DrivePod("UUT", leader, follower1, follower2, shifter);
	}
	
	public void runDrivePodTest() {
		// Create dataset
		XYDataset dataset = new XYSeriesCollection();
		
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        XYSeries throttleSeries = new XYSeries("Throttle (input)");
        XYSeries currentSeries  = new XYSeries("Motor current (simulated)");
        XYSeries gearSeries     = new XYSeries("Transmission gear (output)");
		for(double t = 0; t <= 15; t += 0.01) {
			// Generate stimulus
			double throttle = Math.sin(t * t / (Math.PI));
			double current  = 2.0 * throttle ; // until we replace this with a better approximation
			
			// Feed stimulus to unit under test.
			// Set the current first so that the drive pod can respond to it.
			leader.setOutputCurrent(current);
			uut.setThrottle(throttle);
			
			// Add inputs and outputs to plot
			throttleSeries.add(t, throttle);
			currentSeries .add(t, current );
			gearSeries    .add(t, lastShifterGear? 1 : 0);
		}
		((XYSeriesCollection)dataset).addSeries(throttleSeries);
		((XYSeriesCollection)dataset).addSeries(currentSeries );
		((XYSeriesCollection)dataset).addSeries(gearSeries    );
		renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        renderer.setSeriesShape(2, ShapeUtilities.createDiamond(2.0f));
		
		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Gearshift behavior over time", "Time", "Amps, Volts, high/low",
				dataset);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(200, 200, 200));
		plot.setRenderer(renderer);

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}
	
	/////////////////////////////////////////////////////
	// Methods used to listen to output
	/////////////////////////////////////////////////////
	
	@Override
	public void takeSet(MockSolenoid source, boolean on) {
		 lastShifterGear = on;
	}

	@Override
	public void takeSet(MockMotorController source, double set) {
		if(source == leader) {
			lastLeaderThrottle = set;
		}
	}

	@Override
	public void takeSpeed(MockMotorController source, double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takePosition(MockMotorController source, double pos) {
		// TODO Auto-generated method stub
		
	}

}
