package org.usfirst.frc.team95.robot.commands.drivebase;

public class DriveFromWallToAutoLine extends DriveStraight {
	public static final double INCHES_TO_AUTO_LINE = 10*12;

	public DriveFromWallToAutoLine() {
		super(INCHES_TO_AUTO_LINE);
	}

}
