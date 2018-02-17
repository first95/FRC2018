package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnScale;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnSwitch;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;

public class AnyOurSideSLF extends Strategy {
	private static final double INCHES_FROM_AUTO_LINE_TO_SWITCH = 12; // TODO: Measure
	private static final double INCHES_FROM_AUTO_LINE_TO_SCALE = 48; // TODO: Measure
	
	
	// This strategy assumes we have a cube pre-loaded on the robot.

	
	public static final String DESCRIPTION = "Drag race to scale if hot, collect second cube and score in scale";
	
	public AnyOurSideSLF() {
		// Add all the moves in AdjustStrategy
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor,
			FieldSide whichSideOfTheScaleIsOurColor,
			Robot.StartPosition robotStartingPosition)
	{
        if((robotStartingPosition == StartPosition.MID_LEFT &&
				whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) || 
				(robotStartingPosition == StartPosition.MID_RIGHT &&
				whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT))
        {
			// We're lined up for the switch; go there.
			addSequential(new DriveStraight(INCHES_FROM_AUTO_LINE_TO_SWITCH + DriveFromWallToAutoLine.INCHES_TO_AUTO_LINE));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
        else if((robotStartingPosition == StartPosition.MID_LEFT &&
				whichSideOfTheScaleIsOurColor == FieldSide.LEFT) || 
				(robotStartingPosition == StartPosition.MID_RIGHT &&
				whichSideOfTheScaleIsOurColor == FieldSide.RIGHT))
        {
			// We're lined up for the scale; go there.
			addSequential(new DriveStraight(INCHES_FROM_AUTO_LINE_TO_SCALE + DriveFromWallToAutoLine.INCHES_TO_AUTO_LINE));
			addSequential(new ScoreStartingCubeOnScale());
		}
        else
        {
			// Fallback to an auto run
			addSequential(new DriveFromWallToAutoLine());
		}
	}

}
