package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

	Timer waitTime = new Timer();
	double m_secondsToWait;
	boolean done = false;

	public Wait(double secondsToWait) {
		m_secondsToWait = secondsToWait;
	}

	@Override
	public void initialize() {
		waitTime.reset();
		waitTime.start();
	}

	@Override
	protected void execute() {
		if (waitTime.get() > m_secondsToWait) {
			done = true;
			isFinished();
		}
	}

	@Override
	protected boolean isFinished() {
		waitTime.reset();
		waitTime.stop();
		return done;
	}

}
