package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestCommandD extends Command {
	private static final String NAME = "D";
	private static final String STATUS_LABEL = NAME + " ran: ";
	
	public TestCommandD() {
		requires(Robot.drivebase);
		requires(Robot.collector);

		// Printouts are unreliable.  Let's try smartdashboard.
		SmartDashboard.putBoolean(NAME, true);
		SmartDashboard.putString(STATUS_LABEL, "");
	}
	
	private void appendState(String state) {
		SmartDashboard.putString(STATUS_LABEL, SmartDashboard.getString(STATUS_LABEL, "") + " " + state);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		// System.out.println("Do nothing!");
		return false;
	}
	
	@Override
	protected void initialize() {
		appendState("I");
	}
	
	private boolean firstExecution = true;
	@Override
	protected void execute() {
		if(firstExecution) {
			appendState("X");
			firstExecution = false;
		}
		SmartDashboard.putBoolean(NAME, !SmartDashboard.getBoolean(NAME, false));
	}
	
	@Override
	protected void end() {
		appendState("E");
	}
	
	@Override
	public synchronized void cancel() {
		appendState("C");
		super.cancel();
	}
}
