package org.usfirst.frc.team95.robot.commands.drivebase;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ManuallyControlDrivebase extends Command {
	private boolean wasPressedLastIteration = false;
	
	public ManuallyControlDrivebase() {
		requires(Robot.drivebase);
	}

	@Override
	public synchronized void start() {
		// This method is called once when the command is activated
		wasPressedLastIteration = false;
	}	
	
	@Override
	protected void execute() {
		// This method is called every iteration
		
		if(Robot.oi.isDriveFwdPressed() && wasPressedLastIteration == false) {
			Robot.drivebase.travelStraight(12);
			wasPressedLastIteration = true;
		} else if(Robot.oi.isDriveBckPressed() && wasPressedLastIteration == false) {
			Robot.drivebase.travelStraight(-12);
			wasPressedLastIteration = true;
		} else if(Robot.oi.isDriveFwdPressed()==false && Robot.oi.isDriveBckPressed()==false) {
			wasPressedLastIteration = false;
		}		
	}
	
	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

}
