package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestCommandSequence extends CommandGroup {

	public TestCommandSequence() {
		addSequential(new TestCommandA());
		addParallel(new TestCommandB());
		addParallel(new TestCommandC());
		addSequential(new TestCommandD());
	}
}
