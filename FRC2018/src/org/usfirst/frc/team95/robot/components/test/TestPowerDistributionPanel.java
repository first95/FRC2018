package org.usfirst.frc.team95.robot.components.test;

import org.usfirst.frc.team95.robot.components.PowerDistributionPanelI;

public class TestPowerDistributionPanel implements PowerDistributionPanelI {
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
