package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class DeployRamps extends TimedCommand {
	public static final double HOLD_DURATION_S = 5.0;
	
	public DeployRamps() {
		super(HOLD_DURATION_S);
		requires(Robot.ramps);
	}

	@Override
	protected void execute() {
		Robot.ramps.setLatches(true);
	}
	
	@Override
	protected void end() {
		Robot.ramps.setLatches(false);
	}
	
}
