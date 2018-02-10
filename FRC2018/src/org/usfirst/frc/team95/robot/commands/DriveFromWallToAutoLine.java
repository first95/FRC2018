package org.usfirst.frc.team95.robot.commands;

public class DriveFromWallToAutoLine extends DriveStraight {
	private static final double INCHES_TO_AUTO_LINE = 5*12;  // TODO: Measure

	public DriveFromWallToAutoLine() {
		super(INCHES_TO_AUTO_LINE);
	}

}
