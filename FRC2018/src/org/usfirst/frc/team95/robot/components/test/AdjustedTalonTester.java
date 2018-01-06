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
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;
import org.usfirst.frc.team95.robot.components.DrivePod;

public class AdjustedTalonTester extends JFrame implements TestCanSpeedController.Listener {
	private static final long serialVersionUID = -8288607681504299432L;
	
	AdjustedTalon uut;
	TestPowerDistributionPanel pdp = new TestPowerDistributionPanel();
	private double  lastCommandedLeaderThrottle;
	private double  lastCommandedLeaderSpeed;
	private boolean lastCommandedShift;
	
	public AdjustedTalonTester(String title) {
		super(title);
		
		uut = new AdjustedTalon(new TestCanSpeedController(0, this), pdp);
		
	}

	public void runThrottleTest() {
		// Create dataset
		XYDataset dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Adjusted throttle vs input");
		
		pdp.setVoltage(13.0);
		for(double throttle = -1.0; throttle <= 1.0; throttle += 0.05) {
			uut.set(throttle);
			series1.add(throttle, lastCommandedLeaderThrottle);
		}
		((XYSeriesCollection)dataset).addSeries(series1);

		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Adjusted throttle vs input", "Input throttle", "Adjusted throttle",
				dataset);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(200, 200, 200));

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AdjustedTalonTester example = new AdjustedTalonTester("Adjusted Talon test");
			example.runThrottleTest();
			example.setSize(800, 400);
			example.setLocationRelativeTo(null);
			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example.setVisible(true);
		});
	}

	
	// Methods for TestCanSpeedController.Listener
	@Override
	public void takeSet(int id, double set) {
		lastCommandedLeaderThrottle = set;
	}

	@Override
	public void takeSpeed(int id, double speed) {
		lastCommandedLeaderSpeed = speed;
		
	}

	@Override
	public void takePosition(int id, double pos) {
		// TODO Auto-generated method stub
		
	}

}
