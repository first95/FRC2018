package org.usfirst.frc.team95.robot.commands.collector;

public class EjectCube extends RunChainsForDuration {
	public static final double EJECT_THROTTLE = 1.0;
	public static final double EJECT_TIME_S = 1.0;
	
	public EjectCube() {
		super(EJECT_THROTTLE, EJECT_TIME_S);
	}
}
