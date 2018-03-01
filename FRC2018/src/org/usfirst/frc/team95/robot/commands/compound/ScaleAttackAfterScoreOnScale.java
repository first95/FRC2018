package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.ScalePosition;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAttackAfterScoreOnScale extends CommandGroup
{
	public ScaleAttackAfterScoreOnScale(FieldSide whichSideOfTheScaleIsOurColor,
			ScalePosition robotScalePosition)
	{
		if(whichSideOfTheScaleIsOurColor == FieldSide.LEFT
				&& robotScalePosition == ScalePosition.LEFT)
		{
			addSequential(new Pivot(180));
			addSequential(new DriveStraight(12));
			addSequential(new SweepTurn(-45, 60));
			addSequential(new DriveStraight(12));
			addSequential(new AutoPickUpCubeAfterScore(12));
			addSequential(new DriveStraight(-12));
			addSequential(new Pivot(180));
			addSequential(new SweepTurn(45, 60));
			addSequential(new DriveStraight(12));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if(whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
				&& robotScalePosition == ScalePosition.RIGHT)
		{
			addSequential(new Pivot(180));
			addSequential(new DriveStraight(12));
			addSequential(new SweepTurn(45, 60));
			addSequential(new DriveStraight(12));
			addSequential(new AutoPickUpCubeAfterScore(12));
			addSequential(new DriveStraight(-12));
			addSequential(new Pivot(180));
			addSequential(new SweepTurn(-45, 60));
			addSequential(new DriveStraight(12));
			addSequential(new ScoreStartingCubeOnScale());
		}
	}
}
