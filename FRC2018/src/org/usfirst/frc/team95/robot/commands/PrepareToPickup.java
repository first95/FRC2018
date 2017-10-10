package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Make sure the robot is in a state to pickup soda cans.
 */
public class PrepareToPickup extends CommandGroup {

	/*
	 * Note:
	 * 
	 * This command hasn't been updated to reflect Team 95's robot.
	 * It remains here as an example.
	 * 
	 * 
	 */
	
	public PrepareToPickup() {
		addParallel(new OpenClaw());
		addParallel(new SetWristSetpoint(0));
		addSequential(new SetElevatorSetpoint(0));
	}
}
