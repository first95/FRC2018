package org.usfirst.frc.team95.robot.commands.drivebase;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlDrivebase extends Command {
	
	

	public ManuallyControlDrivebase() {
		requires(Robot.drivebase);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivebase.arcade();
		//Robot.drivebase.autoShift();
		

		// For button shifting
		// Robot.drivebase.setGear(Robot.oi.getHighGear());


	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false; // Runs until interrupted
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivebase.drive(0, 0);
	}
}
