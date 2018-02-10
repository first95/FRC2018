package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.commands.DeployRamps;
import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Ramps extends Subsystem {
	// The solenoids for the piston that opens the maw, and operates the wrist
	private SolenoidI leftRampLatch, rightRampLatch;
	
	public Ramps() {
		super();
		
		// False means it is extended
		leftRampLatch  = new SolenoidWrapper(Constants.LEFT_RAMP_LATCH );
		rightRampLatch = new SolenoidWrapper(Constants.RIGHT_RAMP_LATCH);
	}

	@Override
	protected void initDefaultCommand() {
//		setDefaultCommand(new Nothing());
	}
	
	public void log() {

	}

	
	public void setLatches(boolean retracted) {
		leftRampLatch.set(retracted);
		rightRampLatch.set(retracted);
	}

}
