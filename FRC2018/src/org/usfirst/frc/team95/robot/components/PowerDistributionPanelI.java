/*
 * PowerDistributionPanelI
 * 
 * This interface requires the presence of every method exposed by the WpiLib PowerDistributionPanel class.
 * It only exists to make unit testing easier.
 * 	
 */


package org.usfirst.frc.team95.robot.components;

public interface PowerDistributionPanelI {
	  public double getVoltage();
	  public double getTemperature();
	  public double getCurrent(int channel);
	  public double getTotalCurrent();
	  public double getTotalPower();
	  public double getTotalEnergy();
	  public void resetTotalEnergy();
	  public void clearStickyFaults();
}
