package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.collector.CloseMaw;
import org.usfirst.frc.team95.robot.commands.collector.OpenMaw;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ReleaseCube extends CommandGroup{

	public ReleaseCube() {
		addSequential(new OpenMaw());
		addSequential(new Pause(0.5));
		addSequential(new CloseMaw());
	}

}
