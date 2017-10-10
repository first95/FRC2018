/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team95.robot.Robot;

/**
 * Opens the claw for one second. Real robots should use sensors, stalling
 * motors is BAD!
 */
public class OpenClaw extends TimedCommand {

	/*
	 * Note:
	 * 
	 * This command hasn't been updated to reflect Team 95's robot.
	 * It remains here as an example.
	 * 
	 * 
	 */
	public OpenClaw() {
		super(1);
		requires(Robot.claw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.claw.open();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.claw.stop();
	}
}
