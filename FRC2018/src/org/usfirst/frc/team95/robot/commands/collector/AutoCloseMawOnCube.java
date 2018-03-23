package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * Open the maw, wait for a cube to trip optical rangefinders,
 * then close the maw.  Chains are run inward the whole time.
 * 
 * This move does not:
 * - Approach the cube
 * - Position the wrist
 * - Position the elevator
 * - Move onto the cube
 * 
 * Other commands and/or teleoperated control must be responsible for that.
 *
 */
public class AutoCloseMawOnCube extends Command {
	public static final double INTAKE_THROTTLE = -1.0;
	
	public AutoCloseMawOnCube() {
		requires(Robot.collector);
	}
	
	@Override
	public synchronized void initialize() {
		// Intake
		Robot.collector.setIntakeSpeed(INTAKE_THROTTLE);
	}
	
	@Override
	protected boolean isFinished() {
		// Need at least two to be tripped to count as done
		if(Robot.collector.getNumberOfMawPhotosensorsTripped() >= 2) {
			System.out.println("Got " + Robot.collector.getNumberOfMawPhotosensorsTripped() + "sensors. Closing.");
			return true; 
		} else {
			return false;
		}
	}
	
	@Override
	protected void end() {
		// Make it be shut
		System.out.println("Closing maw");

		Robot.collector.setMawOpen(false);
		Robot.collector.setIntakeSpeed(0);
	}
}
