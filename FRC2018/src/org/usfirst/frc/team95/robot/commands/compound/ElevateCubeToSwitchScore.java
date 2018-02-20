package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevateCubeToSwitchScore extends CommandGroup{

	public ElevateCubeToSwitchScore() {
		addSequential(new SetElevatorHeight(ElevatorHoldPoint.SWITCH_SCORE));
		addSequential(new SetWristAngle(WristAngle.MID_DOWN));
		addSequential(new EjectCube());
	}

}
