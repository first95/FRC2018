package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestCommandB extends Command {
//	public TestCommandB() {
//		requires(Robot.drivebase);
//	}
//	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		// System.out.println("Do nothing!");
		return false;
	}
	
	@Override
	protected void initialize() {
		System.out.println("Starting B");
	}
	
	@Override
	protected void end() {
		System.out.println("Ending B");
	}
	@Override
	public synchronized void cancel() {
		System.out.println("Cancelling B");
		super.cancel();
	}

}
