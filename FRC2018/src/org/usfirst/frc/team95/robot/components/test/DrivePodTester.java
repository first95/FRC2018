package org.usfirst.frc.team95.robot.components.test;

import java.awt.Color;

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

public class DrivePodTester extends JFrame {
	private static final long serialVersionUID = -8250801882870243298L;

	public DrivePodTester(String title) {
		super(title);

		// Create dataset
		XYDataset dataset = createDataset();

		// Create chart
		JFreeChart chart = ChartFactory.createScatterPlot("Test chart", "X-Axis", "Y-Axis",
				dataset);

		// Changes background color
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(new Color(255, 228, 196));

		// Create Panel
		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}

	private XYDataset createDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries series1 = new XYSeries("Series 1");
		series1.add(0, 0);
		series1.add(1, 1);
		series1.add(2, 1.5);
		series1.add(3, 2.7);
		dataset.addSeries(series1);

		XYSeries series2 = new XYSeries("Series 2");
		series2.add(0, 7);
		series2.add(1, 3);
		series2.add(2, 5.5);
		series2.add(3, 6);
		dataset.addSeries(series2);

		return dataset;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			DrivePodTester example = new DrivePodTester("Drive pod test");
			example.setSize(800, 400);
			example.setLocationRelativeTo(null);
			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example.setVisible(true);
		});
	}

}
