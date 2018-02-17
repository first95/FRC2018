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
public class SweepTurn extends Command {
	double degreesCw;
	double turnRadiusInches;
	
	public SweepTurn(double degreesCw, double turnRadiusInches) {
		requires(Robot.drivebase);
		
		this.degreesCw = degreesCw;
		this.turnRadiusInches = turnRadiusInches;
	}

	// Called every time the command starts
	@Override
	public void initialize() {
		System.out.println("Starting SweepTurn (" + degreesCw + " degrees at " + turnRadiusInches + " inches radius)");

		// Command the movement
		Robot.drivebase.travelSweepingTurnForward(degreesCw, turnRadiusInches);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.drivebase.onTarget();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		System.out.println("Ending SweepTurn (" + degreesCw + " degrees at " + turnRadiusInches + " inches radius)");
		Robot.drivebase.drive(0, 0);
	}
}
