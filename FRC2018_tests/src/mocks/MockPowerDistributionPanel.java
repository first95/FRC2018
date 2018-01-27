/*
 * MockPowerDistributionPanel.java
 * 
 * This class acts like a PDP object, except it doesn't require the robot
 * to operate, and doesn't interact with any hardware at all.  Instead, it
 * reports outputs that can be controlled by a tester.
 * 
 * This is useful for unit testing.
 * 
 * 
 */
package mocks;

import org.usfirst.frc.team95.robot.components.PowerDistributionPanelI;

public class MockPowerDistributionPanel implements PowerDistributionPanelI {
	public static final int NUM_CHANNELS = 12;
	private double voltage;
	private double temperature;
	private double[] current = new double[NUM_CHANNELS];
	
	public void setVoltage(double value) { voltage = value; }
	public void setTemperature(double value) { temperature = value; }
	public void setCurrent(double value, int channel) { current[channel] = value; }
	
	
	@Override
	public double getVoltage() { return voltage; }

	@Override
	public double getTemperature() { return temperature; }

	@Override
	public double getCurrent(int channel) { return current[channel]; }

	@Override
	public double getTotalCurrent() {
		double total = 0;
		for (double c : current) {
			total += c;
		}
		return total;
	}

	@Override
	public double getTotalPower() {
		return getTotalCurrent() * getVoltage();
	}

	@Override
	public double getTotalEnergy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void resetTotalEnergy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearStickyFaults() {
		// TODO Auto-generated method stub

	}
}
