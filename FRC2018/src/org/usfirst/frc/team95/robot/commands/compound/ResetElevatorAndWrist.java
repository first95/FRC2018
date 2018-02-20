package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ResetElevatorAndWrist extends CommandGroup {

	public ResetElevatorAndWrist() {
		addSequential(new DriveStraight(-12));
		addSequential(new SetWristAngle(WristAngle.UP));
		addSequential(new SetElevatorHeight(ElevatorHoldPoint.FLOOR));
	}

}
