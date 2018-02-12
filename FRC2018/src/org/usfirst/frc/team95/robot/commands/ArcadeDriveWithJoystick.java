/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team95.robot.Robot;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class ArcadeDriveWithJoystick extends Command {

	private double leftSpeed = Robot.drivebase.getLeftSpeed();
	private double rightSpeed = Robot.drivebase.getRightSpeed();
	private Timer shiftTimer = new Timer();
	private boolean allowShift = true;

	public ArcadeDriveWithJoystick() {
		requires(Robot.drivebase);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {

		Robot.drivebase.arcade();

		// Autoshift framework based off speed

		if (allowShift) {
			if ((leftSpeed < 5.0) && (rightSpeed < 5.0)) {

				Robot.drivebase.setGear(false);
			}
		}

		if (allowShift) {
			if ((leftSpeed > 5.0) && (rightSpeed > 5.0)) {
				shiftTimer.reset();
				shiftTimer.start();
				allowShift = false;
				Robot.drivebase.setGear(true);
			}
		} else if (shiftTimer.get() == 2.0) {
			allowShift = true;
			shiftTimer.stop();
		}
		
		Robot.drivebase.setGear(Robot.oi.getHighGear());

	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivebase.drive(0, 0);
	}
}
