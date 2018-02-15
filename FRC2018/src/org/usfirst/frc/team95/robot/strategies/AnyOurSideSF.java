package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnSwitch;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;

public class AnyOurSideSF extends Strategy {
	// The distance the robot must move to go from having its back flush with the wall
	// to having its front flush with the switch.
	private static final double INCHES_FROM_WALL_TO_SWITCH = 101.6; // Measured in solidworks, 2018-2-15
	
	
	// This strategy assumes we have a cube pre-loaded on the robot.
	// Drives forward to the auto line.
	// If the robot started on the "hot" side of the switch, score the
	// cube afterward.
	
	public static final String DESCRIPTION = "Drive Forward and Decide to Score Cube on Switch";
	
	public AnyOurSideSF() {
		
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor,
			FieldSide whichSideOfTheScaleIsOurColor,
			Robot.StartPosition robotStartingPosition) {
		// We've already added the segment that drives to the switch.
		if((robotStartingPosition == StartPosition.MID_LEFT &&
				whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) || 
				(robotStartingPosition == StartPosition.MID_RIGHT &&
				whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)) {
			// Robot is on the correct side, score the cube after reaching
			// the auto line.
			addSequential(new DriveStraight(INCHES_FROM_WALL_TO_SWITCH));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else if((robotStartingPosition == StartPosition.LEFT &&
				whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) || 
				(robotStartingPosition == StartPosition.RIGHT &&
				whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)) {
			// Robot is on the correct side, score the cube after reaching
			// the auto line.
		} else if (robotStartingPosition != StartPosition.CENTER) { 
			// There's a side mismatch but we can at least cross the auto line
			addSequential(new DriveFromWallToAutoLine());
		} else {
			// We're in the center; do nothing
		}
	}

}
