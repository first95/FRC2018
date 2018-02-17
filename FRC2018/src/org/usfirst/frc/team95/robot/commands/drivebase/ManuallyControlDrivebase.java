package org.usfirst.frc.team95.robot.commands.drivebase;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlDrivebase extends Command {
	private boolean wasPressedLastIteration = false;
	
	public ManuallyControlDrivebase() {
		requires(Robot.drivebase);
	}

	@Override
	public synchronized void initialize() {
		// This method is called once when the command is activated
		wasPressedLastIteration = false;
	}	
	
	@Override
	protected void execute() {
		// This method is called every iteration
		Robot.drivebase.arcade();
		Robot.drivebase.setGear(Robot.oi.getHighGear());
		
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
