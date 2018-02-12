package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * The main autonomous command to pickup and deliver the soda to the box.
 */
public class GoToSwitch extends CommandGroup {
	
	public GoToSwitch() {
		addSequential(new DriveStraight(10));
	}
}
