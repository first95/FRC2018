/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

/**
 * Have the robot drive tank style using the PS3 Joystick until interrupted.
 */
public class ArcadeDriveWithJoystick extends Command {

	public ArcadeDriveWithJoystick() {
		requires(Robot.drivebase);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
		Robot.drivebase.arcade(Constants.driveStick, true);
		Robot.drivebase.setGear(Robot.oi.getJoystick().getRawButton(Constants.SHIFT_BUTTON));
		
//		SmartDashboard.putNumber("test", Robot.oi.getJoystick().getY());
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
