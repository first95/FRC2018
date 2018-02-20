package org.usfirst.frc.team95.robot.commands.drivebase;

public class AnyForward extends DriveStraight {
	public static final double INCHES_TO_AUTO_LINE = 97; // Takes us to within 4" of the switch but doesn't make contact.  Measured in Solidworks, 2018-2-15

	public AnyForward() {
		super(INCHES_TO_AUTO_LINE);
	}
}
