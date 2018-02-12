package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreCollectedCubeOnSwitch extends CommandGroup {

	// This command assumes the cube starts in the maw, with the wrist up
	public ScoreCollectedCubeOnSwitch() {
		addSequential(new SetElevatorHeight(ElevatorHoldPoint.SWITCH_SCORE));
		addSequential(new SetWristAngle(WristAngle.MID_UP));
		addSequential(new EjectCube());
	}
}
