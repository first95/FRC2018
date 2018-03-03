package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class Pause extends TimedCommand {
	// This move waits and does nothing for a specified duration
	public Pause(double pauseDurationS) {
		super(pauseDurationS);
	}
}
