package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestCommandC extends TimedCommand {
	String name = "C";

	public TestCommandC() {
		super(5); // Run 5 seconds
		requires(Robot.drivebase);

		// Printouts are unreliable.  Let's try smartdashboard.
		SmartDashboard.putBoolean(name, true);
	}
	
	@Override
	protected void initialize() {
		System.out.println("Starting C");
	}
	
	private boolean firstExecution = true;
	@Override
	protected void execute() {
		if(firstExecution) {
			System.out.println("First execution of C");
			firstExecution = false;
		}
		SmartDashboard.putBoolean(name, !SmartDashboard.getBoolean(name, false));
	}
	
	@Override
	protected void end() {
		System.out.println("Ending C");
	}
	
	@Override
	public synchronized void cancel() {
		System.out.println("Cancelling C");
		super.cancel();
	}

}
