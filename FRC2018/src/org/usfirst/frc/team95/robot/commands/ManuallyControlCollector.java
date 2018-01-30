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
		Robot.collector.setMawOpen(Robot.oi.getCollectorOpen());
		Robot.collector.setIntakeSpeed(Robot.oi.getCollectorSpeed());
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
