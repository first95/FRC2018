package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestCommandD extends Command {
	public TestCommandD() {
		requires(Robot.drivebase);
	}
	

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		// System.out.println("Do nothing!");
		return false;
	}
	
	@Override
	protected void initialize() {
		System.out.println("Starting D");
	}
	
	private boolean firstExecution = true;
	@Override
	protected void execute() {
		if(firstExecution) {
			System.out.println("First execution of D");
			firstExecution = false;
		}
	}
	
	@Override
	public synchronized void cancel() {
		System.out.println("Cancelling D");
		super.cancel();
	}
	
	@Override
	protected void end() {
		System.out.println("Ending D");
	}

}
