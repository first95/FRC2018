/*
 * PowerDistributionPanelWrapper
 * 
 * This class wraps the PowerDistributionPanel class, but also implements the PowerDistributionPanelI
 * interface. 
 */
package org.usfirst.frc.team95.robot.components;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class PowerDistributionPanelWrapper implements PowerDistributionPanelI {
	private PowerDistributionPanel wrapped;
	
	public PowerDistributionPanelWrapper() {
		wrapped = new PowerDistributionPanel();
	}
	
	@Override
	public double getVoltage() { return wrapped.getVoltage(); }

	@Override
	public double getTemperature() { return wrapped.getTemperature(); }

	@Override
	public double getCurrent(int channel) {  return wrapped.getCurrent(channel); }

	@Override
	public double getTotalCurrent() { return wrapped.getTotalCurrent(); }

	@Override
	public double getTotalPower() { return wrapped.getTotalPower(); }

	@Override
	public double getTotalEnergy() { return wrapped.getTotalEnergy(); }

	@Override
	public void resetTotalEnergy() { wrapped.resetTotalEnergy(); }

	@Override
	public void clearStickyFaults() { wrapped.clearStickyFaults(); }

}
