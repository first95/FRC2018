package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreStartingCubeOnSwitch extends CommandGroup {

	// This command assumes the cube starts in the maw, with the wrist up
	public ScoreStartingCubeOnSwitch() {
		//addSequential(new ElevateCubeAndScore(ElevatorHoldPoint.SWITCH_SCORE, true));
		addSequential(new SetWristAngle(WristAngle.MID_UP));
		
		// If the elevator is raised up during this move then use ReleaseCube
		// However, if the elevator is not used then EjectCube is better
		addSequential(new EjectCube());
		addSequential(new DriveStraight(-20.0));
		addSequential(new ResetElevatorAndWrist(false));
	}
}
