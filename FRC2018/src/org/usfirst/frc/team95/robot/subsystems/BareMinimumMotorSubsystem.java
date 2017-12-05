package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.commands.SetMotorSpeed;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

public class BareMinimumMotorSubsystem extends Subsystem {
	// Here you put the objects to interface with the physical system.
	// Make them private, so that we know for sure all interactions with them go through this class's methods.
	private CANTalon motor = new CANTalon(73);
	
	public BareMinimumMotorSubsystem() {
		super(); // Call the parent class's constructor (in this case Subsystem)
		
		// If you need to set up default options, do it here.
		// This code will be called once when the program starts.
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new SetMotorSpeed());
	}

	// Here is where you write methods that perform actions specific to this subsystem.
	
	/**
	 * 
	 * @param throttle - 1.0 for full power forward, 0.0 for stationary, -1.0 for full power reverse
	 */
	public void setMotorThrottle(double throttle) {
		motor.set(throttle);
	}
}
