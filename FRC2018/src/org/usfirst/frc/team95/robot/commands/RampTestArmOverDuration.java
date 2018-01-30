package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class RampTestArmOverDuration extends TimedCommand {
	double startValue, endValue;
	double durationSeconds;
	
	public RampTestArmOverDuration(double seconds, double startValue, double endValue) {
		super(seconds);
		this.durationSeconds = seconds;
		this.startValue = startValue;
		this.endValue   = endValue;
		requires(Robot.testArm);
	}
//	
//	@Override
//	public synchronized void start() {
//		
//	}

	@Override
	protected void execute() {
		double elapsedSeconds = timeSinceInitialized();
		double fractionDone = elapsedSeconds / durationSeconds;
		double value = startValue + fractionDone * (endValue - startValue);
		
		Robot.testArm.setMotor(value);
	}
	
	@Override
	protected void end() {
		Robot.testArm.stopMotor();
	}

}
