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
	public void setPulseDuration(double durationSeconds) { ((SolenoidI) wrapped).setPulseDuration(durationSeconds); }

	@Override
	public void startPulse() { ((SolenoidI) wrapped).startPulse(); }

}
