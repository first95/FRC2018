package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BareMinimumPneumaticSubsystem extends Subsystem {
	// Here you put the objects to interface with the physical system.
	// Make them private, so that we know for sure all interactions with them go through this class's methods.
	private Solenoid piston = new Solenoid(2); // Currently set to Orville's "gear pooper" solenoid
	
	@Override
	protected void initDefaultCommand() {
		// No default command
	}
	
	// Here is where you write methods that perform actions specific to this subsystem.
	public void ExtendOrRetract(boolean extend) {
		piston.set(extend);
	}

}
