package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class RunChainsForDuration extends TimedCommand {
	private double throttleOutward = 1.0;
	public static final double EJECT_TIME_S = 0.25;
	
	public RunChainsForDuration(double throttleOutward, double duration) {
		super(duration);
		requires(Robot.collector);
		this.throttleOutward = throttleOutward;
	}

	@Override
	protected void execute() {
		Robot.collector.setIntakeSpeed(throttleOutward);
	}
	
	@Override
	protected void end() {
		Robot.collector.setIntakeSpeed(0);
	}

}
