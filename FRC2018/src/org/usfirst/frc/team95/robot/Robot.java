package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import org.usfirst.frc.team95.robot.subsystems.Climber;
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
	/**
	 * Robot position at match start.
	 * Robot is assumed to have its bumper flush against the alliance wall
	 * in all these cases.
	 */
	
	public enum StartPosition {
		LEFT,      // Rear left corner of the bumper touches the diagonal of the left portal
		CENTER,    // Robot is centered on the field centerline
		RIGHT,     // Rear right corner of the bumper touches the diagonal of the right portal
	}
	
	/**
	 * Robot position after scoring on the scale. Robot is assumed to be centered
	 * on the switch and have it's front bumper 10 inches back from the end of the scale
	 * plate in all of these cases.
	 */
	private StartPosition robotStartSide; // The location where the robot began
	private String gameData;
	private Command autonomousCommand;

	// Components of the robot
	public static DriveBase drivebase;
	public static Collector collector;
	public static Climber climber;
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
		climber = new Climber();
		compressor = new Compressor();
		oi = new OI();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivebase);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(collector);

		// Disable brakes on talons to make it
		// easier to push
		drivebase.brake(false);
		elevator.brake(false);
		climber.brake(false);
		
	}

	@Override
	public void autonomousInit() {
		
		// Lock auto into first gear
		Robot.oi.setShiftLockValue(-1);
		
		int maxTime_sec = 8;
		double startTime_sec = Timer.getFPGATimestamp();
		double elapTime_sec = Timer.getFPGATimestamp() - startTime_sec;
		gameData = "";
		while ((gameData.length() < 3) & (elapTime_sec < maxTime_sec)) {
			gameData = DriverStation.getInstance().getGameSpecificMessage();
			elapTime_sec = Timer.getFPGATimestamp() - startTime_sec;
		}
		if (gameData == "") {
			gameData = "UUU";
		} else {
			System.out.println("Time to get game data was "+elapTime_sec+" seconds.");
		}
		System.out.println("Plate assignments are " + gameData);

		robotStartSide = oi.getRobotStartPosition();
		System.out.println("Robot start side: " + robotStartSide);
		System.out.println("The " + getWhichSideOfTheNearSwitchIsOurColor() + " side of the near switch is our color.");
		System.out.println("The " + getWhichSideOfTheScaleIsOurColor() + " side of the scale is our color.");
		System.out.println("The " + getWhichSideOfTheFarSwitchIsOurColor() + " side of the far switch is our color.");
		
		autonomousCommand = oi.getSelectedCommand(getWhichSideOfTheNearSwitchIsOurColor(), getWhichSideOfTheScaleIsOurColor());
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		commonPeriodic();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
	 */
	public void disabledInit() {
		drivebase.brake(false);
		elevator.brake(false);
		climber.brake(false);
	}

	public void disabledPeriodic() {	
		commonPeriodic();
	}
	
	public void commonPeriodic() {
		Scheduler.getInstance().run(); // Runs all active commands
		elevator.checkAndApplyHomingSwitch();
        drivebase.pullPidConstantsFromSmartDash();
        oi.visit();
        drivebase.visit();
        
        // Depending if you want all output or just limited
        // use either debugLog() or just log()
		//debugLog();
        log();
	}

	@Override
	public void teleopInit() {
		
		// Unlock the auto shifter
		Robot.oi.setShiftLockValue(0);
		
		drivebase.brake(true);
		elevator.brake(true);
		climber.brake(true);

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
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		commonPeriodic();
		LiveWindow.run();
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	private void log() {
		//drivebase.log();
		//elevator.log();
		//collector.log();
		//oi.log();
	}
	
	private void debugLog() {
		drivebase.log();
		elevator.log();
		collector.log();
		oi.log();
		climber.log();
	}

	public Robot.StartPosition getRobotStartSide() {
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
	// The side of the scale that belongs to us
	public  FieldSide getWhichSideOfTheScaleIsOurColor() {
		return sideFromChar(gameData.charAt(1));
	}
	// The side of the far  switch that belongs to us
	public  FieldSide getWhichSideOfTheFarSwitchIsOurColor() {
		return sideFromChar(gameData.charAt(2));
	}
}
