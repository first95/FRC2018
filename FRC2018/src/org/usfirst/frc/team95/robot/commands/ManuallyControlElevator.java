package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManuallyControlElevator extends Command {
	// In (feet per iteration)/(joystick units)
	private static final double SPEED_CONTROL_SENSITIVITY = 4000;
	private double targetPosition;
	
	public ManuallyControlElevator() {
		requires(Robot.elevator);
		targetPosition = 0;
		Robot.elevator.setCurrentPosToZero();
	}

	@Override
	protected void execute() {
//		Robot.elevator.setElevatorSpeed(Robot.oi.getElevatorSpeed());
		double speed_feet_per_iteration = Robot.oi.getElevatorSpeed() * SPEED_CONTROL_SENSITIVITY;
		targetPosition += speed_feet_per_iteration;
		Robot.elevator.setElevatorHeight(targetPosition); 
		SmartDashboard.putNumber("Target elevator height", targetPosition);
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
