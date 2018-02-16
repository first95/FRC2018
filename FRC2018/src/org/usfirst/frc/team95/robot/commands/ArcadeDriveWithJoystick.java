/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.subsystems.DriveBase;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class ArcadeDriveWithJoystick extends Command {

	private double leftSpeed;
	private double rightSpeed;
	private Timer shiftTimer = new Timer();
	private boolean allowShift = true;
	private boolean allowDeshift = true;
	private boolean hasAlreadyShifted = false;

	public ArcadeDriveWithJoystick() {
		requires(Robot.drivebase);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivebase.arcade();

		leftSpeed = Robot.drivebase.getLeftSpeed();
		rightSpeed = Robot.drivebase.getRightSpeed();

		// Autoshift framework based off speed
		if (allowShift) {
			if ((leftSpeed < 5.0) && (rightSpeed < 5.0)) {
				Robot.drivebase.setGear(false);

				if (hasAlreadyShifted) {
					allowDeshift = true;
					hasAlreadyShifted = false;
				}

			} else if ((leftSpeed > 5.0) && (rightSpeed > 5.0)) {
				if (allowDeshift) {
					shiftTimer.reset();
					shiftTimer.start();
					allowShift = false;
					Robot.drivebase.setGear(true);
				}
			}
		} else if (shiftTimer.get() > 1.0) {
			allowShift = true;
			shiftTimer.stop();
			shiftTimer.reset();
			allowDeshift = false;
			hasAlreadyShifted = true;
		}

		// Robot.drivebase.setGear(Robot.oi.getHighGear());
		SmartDashboard.putBoolean("Allow Shift:", allowShift);
		SmartDashboard.putBoolean("Allow Deshift:", allowDeshift);
		SmartDashboard.putBoolean("Has Already Shifted:", hasAlreadyShifted);

	}

	public void log() {
		
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
