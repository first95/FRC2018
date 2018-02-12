package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.commands.compound.ScoreCollectedCubeOnSwitch;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;

public class DriveForwardAndMaybeScoreCubeOnSwitch extends Strategy {
	private static final double INCHES_FROM_AUTO_LINE_TO_SWITCH = 12; // TODO: Measure
	
	
	// This strategy assumes we have a cube pre-loaded on the robot.
	// Drives forward to the auto line.
	// If the robot started on the "hot" side of the switch, score the
	// cube afterward.
	
	public static final String DESCRIPTION = "Drive Forward and Decide to Score Cube on Switch";
	
	public DriveForwardAndMaybeScoreCubeOnSwitch() {
		// We always drive forward, regardless of location
		addSequential(new DriveFromWallToAutoLine());
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
			FieldSide whichSideOfTheFarSwitchIsOurColor, FieldSide robotStartingPosition) {
		// We've already added the segment that drives to the switch.
		if(robotStartingPosition == whichSideOfTheNearSwitchIsOurColor) {
			// Robot is on the correct side, score the cube after reaching
			// the auto line.
			addSequential(new DriveStraight(INCHES_FROM_AUTO_LINE_TO_SWITCH));
			addSequential(new ScoreCollectedCubeOnSwitch());
		} else {
			// Do nothing additional
		}
	}

}
