package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BareMinimumPneumaticSubsystem extends Subsystem {
	Solenoid piston = new Solenoid(4); // Make sure to change this to the right number
	@Override
	protected void initDefaultCommand() {
	}
	
	public void ExtendOrRetract(boolean extend) {
		piston.set(extend);
	}

}
