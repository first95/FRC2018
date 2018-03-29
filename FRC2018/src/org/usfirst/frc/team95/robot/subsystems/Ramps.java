package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.commands.TriggerRampRelease;
import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Ramps extends Subsystem {
	// The solenoids for the piston that opens the maw, and operates the wrist
	private SolenoidI rampLatch;
	
	public Ramps() {
		super();
		
		// False means it is extended
		rampLatch  = new SolenoidWrapper(Constants.RAMP_LATCH);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Nothing());
	}
	
	public void log() {

	}

	
	public void setLatches(boolean retracted) {
		rampLatch.set(retracted);
	}

}
