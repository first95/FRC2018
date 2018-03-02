package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class TriggerRampRelease extends Command {
	
	public TriggerRampRelease() {
		requires(Robot.ramps);
	}

	@Override
	protected void initialize() {
		Robot.ramps.setLatches(true);
	}
	
	@Override
	protected void end() {
		Robot.ramps.setLatches(false);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
	
}
