package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestCommandB extends Command {
	String name = "B";
	
	public TestCommandB() {
		requires(Robot.drivebase);

		// Printouts are unreliable.  Let's try smartdashboard.
		SmartDashboard.putBoolean(name, true);
	}
	
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
	
	private boolean firstExecution = true;
	@Override
	protected void execute() {
		if(firstExecution) {
			System.out.println("First execution of B");
			firstExecution = false;
		}
		SmartDashboard.putBoolean(name, !SmartDashboard.getBoolean(name, false));
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
