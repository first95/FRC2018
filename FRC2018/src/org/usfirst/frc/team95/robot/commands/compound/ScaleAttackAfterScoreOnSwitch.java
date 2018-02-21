package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.SwitchPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAttackAfterScoreOnSwitch extends CommandGroup
{
	private static final double INCHES_TO_MOVE_AFTER_SWITCH_SCORE = 16;
	private static final double INCHES_TO_GO_TO_SCALE = 50;
	private static final double INCHES_TO_GO_TO_SCALE_AFTER_SWEEPER_TURN = 0;
	private static final double INCHES_TO_GO_TO_SWITCH_R_L = 0;
	private static final double GET_TO_NEW_CUBE = 76;
	private static final double RUN_INTO_CUBE = 30.5;
	private static final double DEGREES_FOR_SWEEPER_TURN = 90;
	private static final double INCHES_FOR_SWEEPER_TURN_RADIUS = 24;
	
	public ScaleAttackAfterScoreOnSwitch(FieldSide whichSideOfTheScaleIsOurColor,
			SwitchPosition whichSideOfTheNearSwitchAreWePoistioned)
	{
		if(whichSideOfTheScaleIsOurColor == FieldSide.LEFT
			&& whichSideOfTheNearSwitchAreWePoistioned == SwitchPosition.LEFT)
		{
			addSequential(new DriveStraight(-INCHES_TO_MOVE_AFTER_SWITCH_SCORE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(GET_TO_NEW_CUBE));
			addSequential(new Pivot(135));
			addSequential(new AutoPickUpCubeAfterScore(RUN_INTO_CUBE));
			addSequential(new Pivot(-309.8));
			addSequential(new DriveStraight(INCHES_TO_GO_TO_SCALE));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if(whichSideOfTheScaleIsOurColor == FieldSide.LEFT
			&& whichSideOfTheNearSwitchAreWePoistioned == SwitchPosition.RIGHT)
		{
			addSequential(new DriveStraight(-INCHES_TO_MOVE_AFTER_SWITCH_SCORE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(GET_TO_NEW_CUBE));
			addSequential(new Pivot(-135));
			addSequential(new AutoPickUpCubeAfterScore(RUN_INTO_CUBE));
			addSequential(new DriveStraight(-3));
			addSequential(new Pivot(45));
			addSequential(new DriveStraight(INCHES_TO_GO_TO_SWITCH_R_L));
			addSequential(new SweepTurn(DEGREES_FOR_SWEEPER_TURN,INCHES_FOR_SWEEPER_TURN_RADIUS));
			addSequential(new DriveStraight(INCHES_TO_GO_TO_SCALE_AFTER_SWEEPER_TURN));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if(whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
			&& whichSideOfTheNearSwitchAreWePoistioned == SwitchPosition.LEFT)
		{
			
		}
		else if(whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
			&& whichSideOfTheNearSwitchAreWePoistioned == SwitchPosition.RIGHT)
		{
			
		}
		else
		{
			
		}
	}
}
