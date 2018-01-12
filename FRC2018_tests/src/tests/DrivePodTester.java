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
import org.usfirst.frc.team95.robot.components.DrivePod;

import com.ctre.phoenix.motorcontrol.ControlMode;

import mocks.MockMotorController;
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
        int seriesNum = 0;
		for(double t = 0; t <= 20; t += 0.1) {
			// TODO: Insert functions for throttle and current here
			leader.setOutputCurrent(0.0);
			uut.setThrottle(1.0);
			
			// TODO: add results to plot
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
