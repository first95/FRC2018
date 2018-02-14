package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnSwitch;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;

public class AnyOurSideSF extends Strategy {
	private static final double INCHES_FROM_AUTO_LINE_TO_SWITCH = 12; // TODO: Measure
	
	
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
			addSequential(new DriveStraight(DriveFromWallToAutoLine.INCHES_TO_AUTO_LINE
					+ INCHES_FROM_AUTO_LINE_TO_SWITCH));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else {
			// We always drive forward, regardless of location
			addSequential(new DriveFromWallToAutoLine());
		}
	}

}
