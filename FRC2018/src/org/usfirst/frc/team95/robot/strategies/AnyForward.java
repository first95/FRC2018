package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;

public class AnyForward extends Strategy {
	
	// This strategy drives past the auto line, unless we're in the center.

	
	public static final String DESCRIPTION = "Drive forward past auto line";
	
	public AnyForward() {
		
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor,
			FieldSide whichSideOfTheScaleIsOurColor,
			Robot.StartPosition robotStartingPosition) {
		if(robotStartingPosition != Robot.StartPosition.CENTER) {
			addSequential(new DriveFromWallToAutoLine());
		} else {
			// Don't want to crash into the cubes
		}
	}

}
