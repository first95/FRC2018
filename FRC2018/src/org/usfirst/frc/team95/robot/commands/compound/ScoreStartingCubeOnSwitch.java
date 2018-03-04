package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreStartingCubeOnSwitch extends CommandGroup {

	// This command assumes the cube starts in the maw, with the wrist up
	public ScoreStartingCubeOnSwitch() {
		addSequential(new ElevateCubeAndScore(ElevatorHoldPoint.SWITCH_SCORE, true));
		addSequential(new ResetElevatorAndWrist());
	}
}
