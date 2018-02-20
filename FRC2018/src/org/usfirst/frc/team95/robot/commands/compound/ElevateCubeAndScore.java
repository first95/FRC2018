package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.Wait;
import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevateCubeAndScore extends CommandGroup {

	public ElevateCubeAndScore(ElevatorHoldPoint position) {
		addParallel(new SetElevatorHeight(position));
		addParallel(new SetWristAngle(WristAngle.MID_UP));
		addSequential(new Wait(0.5));
		addSequential(new EjectCube());
	}

}
