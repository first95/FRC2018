package org.usfirst.frc.team95.robot.commands;

import java.io.Console;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetMotorSpeed extends Command {
	public SetMotorSpeed() {
		requires(Robot.bmms);
	}

	@Override
	protected void execute() {
		// This method is called once every loop through the robot code.
		double throttle = Constants.weaponStick.getRawAxis(Constants.EXAMPLE_MOTOR_AXIS);
		Robot.bmms.setMotorThrottle(throttle);
	}
	
	@Override
	protected boolean isFinished() {
		// When this method returns true, execute() will no longer be called.
		return false; // Runs until interrupted
	}

}
