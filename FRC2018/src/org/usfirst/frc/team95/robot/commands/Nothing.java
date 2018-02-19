package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class Nothing extends Command {

	@Override
	protected boolean isFinished() {
		// This move sits and waits.
		// This is actually really useful at the end of certain moves.
		return false;
	}

}
