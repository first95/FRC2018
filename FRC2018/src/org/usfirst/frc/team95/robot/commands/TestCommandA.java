package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class TestCommandA extends TimedCommand {
	
	public TestCommandA() {
		super(5); // Run 5 seconds
		requires(Robot.drivebase);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Starting A");
	}
	
	private boolean firstExecution = true;
	@Override
	protected void execute() {
		if(firstExecution) {
			System.out.println("First execution of A");
			firstExecution = false;
		}
	}
	
	@Override
	protected void end() {
		System.out.println("Ending A");
	}
	
	@Override
	public synchronized void cancel() {
		System.out.println("Cancelling A");
		super.cancel();
	}

}
