package mocks;

import org.usfirst.frc.team95.robot.components.SolenoidI;

public class MockSolenoid implements SolenoidI {
	boolean setpoint = false;
	// An interface to be implemented by the class that owns these
	public interface Listener {
		public void takeSet(MockSolenoid source, boolean on);
	}
	private Listener listener;
	
	public MockSolenoid(Listener l) {
		listener = l;
	}
	
	@Override
	public void set(boolean on) {
		setpoint = on;
		listener.takeSet(this, on);
	}

	@Override
	public boolean get() {
		return setpoint;
	}

	@Override
	public boolean isBlackListed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPulseDuration(double durationSeconds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startPulse() {
		// TODO Auto-generated method stub
	}

}
