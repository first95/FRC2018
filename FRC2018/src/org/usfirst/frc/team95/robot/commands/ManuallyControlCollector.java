package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlCollector extends Command {
	
	public ManuallyControlCollector() {
		requires(Robot.collector);
	}

	@Override
	protected void execute() {
		Robot.collector.setMawOpen(Robot.oi.getJoystick().getRawButton(Constants.OPEN_COLLECTOR_BUTTON));
		double collector_speed = Robot.oi.getJoystick().getRawAxis(Constants.COLLECTOR_IN_AXIS)
				- Robot.oi.getJoystick().getRawAxis(Constants.COLLECTOR_OUT_AXIS);
		Robot.collector.setIntakeSpeed(collector_speed);
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
