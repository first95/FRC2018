package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the speed of the chains.  Please note that this does not
 * stop the chains at any point - they'll keep going until you run
 * this automove with a value of 0 or set it in another move.
 *
 */
public class RunChains extends Command {
	private double throttleOutward;
	public static final double EJECT_TIME_S = 0.25;
	
	public RunChains(double throttleOutward) {
		requires(Robot.collector);
		this.throttleOutward = throttleOutward;
	}

	@Override
	protected void initialize() {
		Robot.collector.setIntakeSpeed(throttleOutward);
	}

	@Override
	protected boolean isFinished() {
		return true; // Done immediately
	}

}
