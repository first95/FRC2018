package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SlackTestArm extends Command {
	public SlackTestArm() {
		super();
		requires(Robot.testArm);
	}

	@Override
	protected void execute() {
		Robot.testArm.stopMotor();
	}
	
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

}
