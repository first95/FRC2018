/*
 * SolenoidWrapper.java
 * 
 * This class is a simple container for the WPILib Solenoid class,
 * but also adds compliance to the SolenoidI interface.
 * 
 * Using this class, rather than using the Solenoid object itself,
 * permits easier unit testing.
 * 
 * 
 */
package org.usfirst.frc.team95.robot.components;

import edu.wpi.first.wpilibj.Solenoid;

public class SolenoidWrapper implements SolenoidI {
	private Solenoid wrapped;
	
	public SolenoidWrapper(final int moduleNumber) {
		wrapped = new Solenoid(moduleNumber);
	}
	
	@Override
	public void set(boolean on) { wrapped.set(on); }

	@Override
	public boolean get() { return wrapped.get(); }

	@Override
	public boolean isBlackListed() { return wrapped.get(); }

	@Override
	public void setPulseDuration(double durationSeconds) { wrapped.setPulseDuration(durationSeconds); }

	@Override
	public void startPulse() { wrapped.startPulse(); }

}
