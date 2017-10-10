/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team95.robot.Robot;

/**
 * Move the elevator to a given location. This command finishes when it is
 * within the tolerance, but leaves the PID loop running to maintain the
 * position. Other commands using the elevator should make sure they disable
 * PID!
 */
public class SetElevatorSetpoint extends Command {
	private double setpoint;


	/*
	 * Note:
	 * 
	 * This command hasn't been updated to reflect Team 95's robot.
	 * It remains here as an example.
	 * 
	 * 
	 */
	public SetElevatorSetpoint(double setpoint) {
		this.setpoint = setpoint;
		requires(Robot.elevator);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.elevator.enable();
		Robot.elevator.setSetpoint(setpoint);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.elevator.onTarget();
	}
}
