/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands.drivebase;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team95.robot.Robot;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class DriveStraight extends Command {
//	private PIDController pid;
	double distanceInches;
	
	public DriveStraight(double inches) {
		requires(Robot.drivebase);
		
		// Not sure why this is reversed
		this.distanceInches = -inches;
		
	}

	// Called every time the command starts
	@Override
	public void initialize() {
		System.out.println("Starting DriveStraight (" + distanceInches + " inches)");
		
		// Command the movement
		Robot.drivebase.travelStraight(distanceInches);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.drivebase.onTarget();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("Ending DriveStraight (" + distanceInches + " inches)");
		Robot.drivebase.drive(0, 0);
	}
}