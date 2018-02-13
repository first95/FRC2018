package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * Open the maw, wait for a cube to trip optical rangefinders,
 * then close the maw.
 * 
 * This move does not:
 * - Approach the cube
 * - Position the wrist
 * - Position the elevator
 * - Move onto the cube
 * - Run the chains inward
 * 
 * Other commands and/or teleoperated control must be responsible for that.
 *
 */
public class AutoCloseMawOnCube extends Command {
	
	public AutoCloseMawOnCube() {
		requires(Robot.collector);
	}
	
	@Override
	public synchronized void start() {
		// Make it be open
		Robot.collector.setMawOpen(true);
	}
	
	@Override
	protected boolean isFinished() {
		// Need at least two to be tripped to count as done
		return Robot.collector.getNumberOfMawPhotosensorsTripped() >= 2;
	}
	
	@Override
	protected void end() {
		// Make it be shut
		Robot.collector.setMawOpen(false);
	}

}
