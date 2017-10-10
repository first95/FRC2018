package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Place a held soda can onto the platform.
 */
public class Place extends CommandGroup {

	/*
	 * Note:
	 * 
	 * This command hasn't been updated to reflect Team 95's robot.
	 * It remains here as an example.
	 * 
	 * 
	 */
	public Place() {
		addSequential(new SetElevatorSetpoint(0.25));
		addSequential(new SetWristSetpoint(0));
		addSequential(new OpenClaw());
	}
}
