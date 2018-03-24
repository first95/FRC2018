package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ResetElevatorAndWrist extends CommandGroup {

	public ResetElevatorAndWrist(boolean isScale) {
		addSequential(new SetWristAngle(WristAngle.MID_UP));
		addSequential(new Pause(1.0));
		if (isScale) {
			addSequential(new SetElevatorHeight(ElevatorHoldPoint.FLOOR));
		}
		addSequential(new SetWristAngle(WristAngle.UP));
		//addSequential(new Pause(0.1));
	}

}
