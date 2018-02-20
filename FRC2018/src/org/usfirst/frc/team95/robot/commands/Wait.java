package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {

	Timer waitTime = new Timer();
	double m_secondsToWait;
	boolean done = false;

	public Wait(double secondsToWait) {
		System.out.println("CONSTRUCTOR RUN!");
		m_secondsToWait = secondsToWait;
	}

	@Override
	public void initialize() {
		System.out.println("INITIALIZE RUN!");
		waitTime.reset();
		waitTime.start();

		while (waitTime.get() < m_secondsToWait) {
			done = false;
		}
		
		done = true;
	}

	@Override
	protected boolean isFinished() {
		System.out.println("IS FINISHED RUN!");
		waitTime.reset();
		waitTime.stop();
		return done;
	}

}
