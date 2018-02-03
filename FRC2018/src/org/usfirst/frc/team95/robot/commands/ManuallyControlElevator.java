package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlElevator extends Command {
	// In (feet per iteration)/(joystick units)
	private static final double SPEED_CONTROL_SENSITIVITY = 100;
	
	public ManuallyControlElevator() {
		requires(Robot.elevator);
	}

	@Override
	protected void execute() {
//		Robot.elevator.setElevatorSpeed(Robot.oi.getElevatorSpeed());
		double speed_feet_per_iteration = Robot.oi.getElevatorSpeed() * SPEED_CONTROL_SENSITIVITY;
		double cur_height = Robot.elevator.getElevatorHeightFeet();
		Robot.elevator.setElevatorHeight(cur_height + speed_feet_per_iteration); 
	}
	
	@Override
	public synchronized void cancel() {
		Robot.elevator.stopMotor();
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
