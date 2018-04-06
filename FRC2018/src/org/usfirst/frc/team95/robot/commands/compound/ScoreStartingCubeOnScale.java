package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreStartingCubeOnScale extends CommandGroup
{

	// This command assumes the cube starts in the maw, with the wrist up.
	// It affects when we move the elevator - we wouldn't want to lift the elevator
	// up to the scale with the wrist in a flat position.
	public ScoreStartingCubeOnScale()
	{
		//addSequential(new DriveStraightLockedGears(-18, false));
		addSequential(new ElevateCubeAndScore(ElevatorHoldPoint.SCALE_SCORE_HIGH, false));
		addSequential(new DriveStraight(-4.0));
		addSequential(new ResetElevatorAndWrist(true));
	}
}
