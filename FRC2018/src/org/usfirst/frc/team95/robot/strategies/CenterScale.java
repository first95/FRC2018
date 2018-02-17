package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnScale;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveFromWallToAutoLine;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;

public class CenterScale extends Strategy
{
	public CenterScale()
	{
		//all of the instructions for this auto move are in AdjustStrategy
	}
	
	// This strategy assumes we have a cube pre-loaded on the robot.
	
	private static final double INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION = 12;
	private static final double INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 133.46;
	// This is the distance to travel between the two sweeping turns
	
	private static final double INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION = 155.86;
	private static final double DEGREES_FOR_SWEEP_TURN = 48.55;
	private static final double INCHES_FOR_SWEEP_TURN_RADIUS = 32.0;
	
	public static final String DESCRIPTION = "Go to scale hot side from center position and score";
	
	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
			StartPosition robotStartingPosition)
	{
		if(robotStartingPosition == StartPosition.CENTER && 
				whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(-DEGREES_FOR_SWEEP_TURN, INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(DEGREES_FOR_SWEEP_TURN, INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if (robotStartingPosition == StartPosition.CENTER &&
				whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(DEGREES_FOR_SWEEP_TURN, INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(-DEGREES_FOR_SWEEP_TURN, INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else
		{
			addSequential(new DriveFromWallToAutoLine());
		}
	}	
}
