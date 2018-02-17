package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class EjectCube extends RunChainsForDuration {
	public static final double EJECT_THROTTLE = 1.0;
	public static final double EJECT_TIME_S = 0.25;
	
	public EjectCube() {
		super(EJECT_THROTTLE, EJECT_TIME_S);
	}
}
