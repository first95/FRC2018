package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendOrRetractPiston extends Command {
	private boolean extend;
	public ExtendOrRetractPiston(boolean extend) {
		requires(Robot.bmns);
		this.extend = extend;
	}
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.bmns.ExtendOrRetract(extend);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
