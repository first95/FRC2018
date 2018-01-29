
package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team95.robot.commands.SetTestArmForDuration;
import org.usfirst.frc.team95.robot.commands.SlackTestArm;
import org.usfirst.frc.team95.robot.subsystems.TestArm;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {

	Command autonomousCommand;

	// Actual classes used in the robot
	public static TestArm testArm;
	boolean once = true;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		// Initialize all subsystems
		testArm = new TestArm();
		
		CommandGroup sequence = new CommandGroup();
		sequence.addSequential(new SetTestArmForDuration(5, 0.5));
		sequence.addSequential(new SetTestArmForDuration(5, -0.5));
		sequence.addSequential(new SetTestArmForDuration(5, 0));
		sequence.addSequential(new SlackTestArm());
		autonomousCommand = sequence;
	}

	@Override
	public void autonomousInit() {
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Plate assignments are " + gameData);
		
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		testArm.updateSmartDash();
		log();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		testArm.updateSmartDash();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		testArm.updateSmartDash();
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {

	}
}
