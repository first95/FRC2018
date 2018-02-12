package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.commands.compound.ScoreCollectedCubeOnScale;
import org.usfirst.frc.team95.robot.commands.compound.ScoreCollectedCubeOnSwitch;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;

public class ScoreCubeOnScaleOrSwitch extends Strategy {
	private static final double INCHES_FROM_AUTO_LINE_TO_SWITCH = 12; // TODO: Measure
	private static final double INCHES_FROM_AUTO_LINE_TO_SCALE = 48; // TODO: Measure
	
	
	// This strategy assumes we have a cube pre-loaded on the robot.

	
	public static final String DESCRIPTION = "Drag race to scale if hot, collect second cube and score in scale";
	
	public ScoreCubeOnScaleOrSwitch() {
		// Add all the moves in AdjustStrategy
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
			FieldSide whichSideOfTheFarSwitchIsOurColor, FieldSide robotStartingPosition) {
		if(robotStartingPosition == whichSideOfTheNearSwitchIsOurColor) {
			// We're lined up for the switch; go there.
			addSequential(new DriveStraight(INCHES_FROM_AUTO_LINE_TO_SWITCH + DriveFromWallToAutoLine.INCHES_TO_AUTO_LINE));
			addSequential(new ScoreCollectedCubeOnSwitch());
		} else if(robotStartingPosition == whichSideOfTheScaleIsOurColor) {
			// We're lined up for the scale; go there.
			addSequential(new DriveStraight(INCHES_FROM_AUTO_LINE_TO_SCALE + DriveFromWallToAutoLine.INCHES_TO_AUTO_LINE));
			addSequential(new ScoreCollectedCubeOnScale());
		} else {
			// Fallback to an auto run
			addSequential(new DriveFromWallToAutoLine());
		}
	}

}
