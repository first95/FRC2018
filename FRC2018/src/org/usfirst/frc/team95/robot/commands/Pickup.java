package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Pickup a soda can (if one is between the open claws) and get it in a safe
 * state to drive around.
 */
public class Pickup extends CommandGroup {

	/*
	 * Note:
	 * 
	 * This command hasn't been updated to reflect Team 95's robot.
	 * It remains here as an example.
	 * 
	 * 
	 */
	public Pickup() {
		addSequential(new CloseClaw());
		addParallel(new SetWristSetpoint(-45));
		addSequential(new SetElevatorSetpoint(0.25));
	}
}
