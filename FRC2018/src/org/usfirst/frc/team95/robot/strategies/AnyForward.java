package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;

public class AnyForward extends Strategy {
	
	// This strategy does nothing.

	
	public static final String DESCRIPTION = "Drive forward past auto line";
	
	public AnyForward() {
		addSequential(new DriveFromWallToAutoLine());
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor,
			FieldSide whichSideOfTheScaleIsOurColor,
			Robot.StartPosition robotStartingPosition) {
		// This move is the same in all configurations
	}

}
