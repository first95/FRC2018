
package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import org.usfirst.frc.team95.robot.commands.Rotate;
import org.usfirst.frc.team95.robot.commands.*;
import org.usfirst.frc.team95.robot.subsystems.Collector;
import org.usfirst.frc.team95.robot.subsystems.Elevator;
import org.usfirst.frc.team95.robot.subsystems.DriveBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	public enum FieldSide {
		LEFT,
		CENTER, // The robot can start in the center, but the scale and switches can't be there
		RIGHT,
		UNKNOWN,
	};
	private FieldSide robotStartSide;       // The location where the robot began
	private String gameData;
	
	Command autonomousCommand;
	SendableChooser<Command> autoMoveChooser;
	SendableChooser<FieldSide> robotStartingPosition;
//	SendableChooser a, b, c;

	// Components of the robot
	public static DriveBase drivebase;
	public static Collector collector;
	public static Elevator elevator;
	public static Compressor compressor;
	public static OI oi;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit() {

		// Initialize all subsystems
		drivebase = new DriveBase();
		collector = new Collector();
		elevator = new Elevator();
		compressor = new Compressor();
		oi = new OI();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivebase);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(collector);

		// Sendable Chooser
		autoMoveChooser = new SendableChooser<Command>();
		autoMoveChooser.addDefault("Nothing", new Nothing());
		autoMoveChooser.addObject("Forward 1 foot", new DriveStraight(12.0));
		autoMoveChooser.addObject("Backward 1 foot", new DriveStraight(-12.0));
		autoMoveChooser.addObject("Pivot clockwise 90 degrees", new Pivot(90));
		autoMoveChooser.addObject("Pivot CCW 180 degrees", new Pivot(-180));
		SmartDashboard.putData("Auto Moves?", autoMoveChooser);
		
		robotStartingPosition = new SendableChooser<FieldSide>();
		robotStartingPosition.addDefault("Center", FieldSide.CENTER);
		robotStartingPosition.addObject("Left", FieldSide.LEFT);
		robotStartingPosition.addObject("Right", FieldSide.RIGHT);
		SmartDashboard.putData("Starting side", robotStartingPosition);

		drivebase.brake(false);
	}

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Plate assignments are " + gameData);

		robotStartSide = robotStartingPosition.getSelected();
		System.out.println("Robot start side: " + robotStartSide);
		System.out.println("The " + getWhichSideOfTheNearSwitchIsOurColor() + " side of the near switch is our color.");
		System.out.println("The " + getWhichSideOfTheFarSwitchIsOurColor() + " side of the far switch is our color.");
		System.out.println("The " + getWhichSideOfTheScaleIsOurColor() + " side of the scale is our color.");
		
		autonomousCommand = autoMoveChooser.getSelected();
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run(); // Runs all active commands
		log();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	public void disabledInit() {
		
		drivebase.brake(false);
	}

	public void disabledPeriodic() {	
		commonPeriodic();
		Scheduler.getInstance().run(); // Runs all active commands
		log();
	}
	
	public void commonPeriodic() {
		elevator.checkAndApplyHomingSwitch();
        drivebase.pullPidConstantsFromSmartDash();
	}

	@Override
	public void teleopInit() {
		drivebase.brake(true);

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if(autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		commonPeriodic();
		Scheduler.getInstance().run(); // Runs all active commands
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		drivebase.log();
		elevator.log();
		collector.log();
		oi.log();
	}
	
	public FieldSide getRobotStartSide() {
		return robotStartSide;
	}
	
	private FieldSide sideFromChar(char side) {
		if(side == 'L') {
			return FieldSide.LEFT;
		} else if(side == 'R') {
			return FieldSide.RIGHT;
		} else {
			return FieldSide.UNKNOWN;
		}
	}
	// The side of the near switch that belongs to us
	public  FieldSide getWhichSideOfTheNearSwitchIsOurColor() {
		return sideFromChar(gameData.charAt(0));
	}
	// The side of the far  switch that belongs to us
	public  FieldSide getWhichSideOfTheScaleIsOurColor() {
		return sideFromChar(gameData.charAt(1));
	}
	// The side of the far  switch that belongs to us
	public  FieldSide getWhichSideOfTheFarSwitchIsOurColor() {
		return sideFromChar(gameData.charAt(2));
	}
}
