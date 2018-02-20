package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlCollector extends Command {
	
	public ManuallyControlCollector() {
		requires(Robot.collector);
	}

	@Override
	protected void execute() {
		// Reset Wrists operator is located within the OI class
		Robot.collector.setMawOpen(Robot.oi.getCollectorOpen());
		Robot.collector.setIntakeSpeed(Robot.oi.getCollectorSpeed());
		Robot.collector.setWristStageOneRetracted(Robot.oi.getWristStageOneRetracted());
		Robot.collector.setWristStageTwoRetracted(Robot.oi.getWristStageTwoRetracted());
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
