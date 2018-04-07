package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseMaw extends Command{

	public CloseMaw() {
		requires(Robot.collector);
	}
	
	@Override
	protected void initialize() {
		Robot.collector.setMawOpen(false);
		super.initialize();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}