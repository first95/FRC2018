package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlElevator extends Command {
	
	public ManuallyControlElevator() {
		requires(Robot.elevator);
	}

	@Override
	protected void execute() {
		Robot.elevator.setElevatorSpeed(Robot.oi.getElevatorSpeed());
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
