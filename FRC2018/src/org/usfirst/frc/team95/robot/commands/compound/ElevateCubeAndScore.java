package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevateCubeAndScore extends CommandGroup {

	public ElevateCubeAndScore(ElevatorHoldPoint position) {
		addSequential(new SetElevatorHeight(position));
		addSequential(new SetWristAngle(WristAngle.MID_DOWN));
		addSequential(new EjectCube());
	}

}