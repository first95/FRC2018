package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private Timer holdTimer;
	private boolean runOnce = true;
	private boolean cubeIn = false;
	
	public AutoCloseMawOnCube() {
		requires(Robot.collector);
		holdTimer = new Timer();
	}
	
	@Override
	public void initialize() {
		// Intake
		Robot.collector.setIntakeSpeed(INTAKE_THROTTLE);		
		cubeIn = false;
	}
	
	@Override
	protected void execute() {
		
		if (Robot.collector.getNumberOfMawPhotosensorsTripped() >= 1) {

			if(runOnce) {
				holdTimer.reset();
				holdTimer.start();
				runOnce = false;
			}
			
			if (holdTimer.get() > 0.1) {
				System.out.println("Got " + Robot.collector.getNumberOfMawPhotosensorsTripped() + "sensors. Closing.");
				holdTimer.stop();
				cubeIn = true;
			}
		}
		else
		{
			holdTimer.stop();
			runOnce = true;
		}
	}
	
	@Override
	protected boolean isFinished() {
		// Need at least two to be tripped to count as done
		return cubeIn;
	}
	
	@Override
	protected void end() {
		// Make it be shut
		System.out.println("Closing maw");

		Robot.collector.setMawOpen(false);
		Robot.collector.setIntakeSpeed(0);
	}
}
