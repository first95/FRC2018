
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
import org.usfirst.frc.team95.robot.commands.collector.AutoCloseMawOnCube;
import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnSwitch;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;
import org.usfirst.frc.team95.robot.strategies.AnyForward;
import org.usfirst.frc.team95.robot.strategies.AnyOurSideSF;
import org.usfirst.frc.team95.robot.strategies.AnyOurSideSLF;
import org.usfirst.frc.team95.robot.strategies.CenterScale;
import org.usfirst.frc.team95.robot.strategies.SitStill;
import org.usfirst.frc.team95.robot.strategies.Strategy;
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
		MID_LEFT,  // Robot's center is centered on the left switch plate
		CENTER,    // Robot is centered on the field centerline
		MID_RIGHT, // Robot's center is centered on the right switch plate
		RIGHT,     // Rear right corner of the bumper touches the diagonal of the right portal
	}
	private StartPosition robotStartSide;       // The location where the robot began
	private String gameData;
	
	Command autonomousCommand;
	SendableChooser<Command> singleAutomoveChooser;
	SendableChooser<StartPosition> robotStartingPosition;
	SendableChooser<Strategy> strategyChooser;
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

		// Sendable Chooser for single commands
		singleAutomoveChooser = new SendableChooser<Command>();
		singleAutomoveChooser.addDefault("Nothing", new Nothing());
		singleAutomoveChooser.addDefault("Test sequence", new TestCommandSequence());
//		singleAutomoveChooser.addObject("Forward 1 foot", new DriveStraight(12.0));
//		singleAutomoveChooser.addObject("Backward 1 foot", new DriveStraight(-12.0));
//		singleAutomoveChooser.addObject("Pivot clockwise 90 degrees", new Pivot(90));
//		singleAutomoveChooser.addObject("Pivot CCW 180 degrees", new Pivot(-180));
//		singleAutomoveChooser.addObject("EjectCube", new EjectCube());
//		singleAutomoveChooser.addObject("SetElevatorHeight", new SetElevatorHeight(ElevatorHoldPoint.SWITCH_SCORE));
//		singleAutomoveChooser.addObject("SweepTurn", new SweepTurn(90, 48));
//		singleAutomoveChooser.addObject("ScoreStartingCubeOnSwitch", new ScoreStartingCubeOnSwitch());
		SmartDashboard.putData("Auto Moves?", singleAutomoveChooser);
		
		// For the operators to indicate on which side of the field they placed the robot
		robotStartingPosition = new SendableChooser<StartPosition>();
		robotStartingPosition.addObject("Left",      StartPosition.LEFT);
		robotStartingPosition.addObject("Mid left",  StartPosition.MID_LEFT);
		robotStartingPosition.addDefault("Center",   StartPosition.CENTER);
		robotStartingPosition.addObject("Mid right", StartPosition.MID_RIGHT);
		robotStartingPosition.addObject("Right",     StartPosition.RIGHT);
		SmartDashboard.putData("Starting position", robotStartingPosition);
		
		// Choose strategy
		strategyChooser = new SendableChooser<>();
		strategyChooser.addDefault(AnyForward.DESCRIPTION, new AnyForward());
		strategyChooser.addDefault(AnyOurSideSF.DESCRIPTION,
				new AnyOurSideSF());
		strategyChooser.addDefault(AnyOurSideSLF.DESCRIPTION, 
				new AnyOurSideSLF());
		strategyChooser.addDefault(CenterScale.DESCRIPTION,
				new CenterScale());
		SmartDashboard.putData(strategyChooser);

		drivebase.brake(false);
	}

	@Override
	public void autonomousInit() {
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("Plate assignments are " + gameData);

		robotStartSide = robotStartingPosition.getSelected();
		System.out.println("Robot start side: " + robotStartSide);
		System.out.println("The " + getWhichSideOfTheNearSwitchIsOurColor() + " side of the near switch is our color.");
		System.out.println("The " + getWhichSideOfTheScaleIsOurColor() + " side of the scale is our color.");
		System.out.println("The " + getWhichSideOfTheFarSwitchIsOurColor() + " side of the far switch is our color.");
		
		autonomousCommand = singleAutomoveChooser.getSelected();
		Strategy chosenStrategy = strategyChooser.getSelected();
		chosenStrategy.AdjustStrategy(getWhichSideOfTheNearSwitchIsOurColor(),
				getWhichSideOfTheScaleIsOurColor(),
				robotStartSide);
		
		// When we've done some testing on single commands and are ready to do
		// strategies, enable the following line.
//		autonomousCommand = chosenStrategy;
		//autonomousCommand = new AutoCloseMawOnCube();
//		autonomousCommand = new AutoCloseMawOnCube();
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
	}

	public void disabledPeriodic() {	
		commonPeriodic();
	}
	
	public void commonPeriodic() {
		Scheduler.getInstance().run(); // Runs all active commands
		elevator.checkAndApplyHomingSwitch();
        drivebase.pullPidConstantsFromSmartDash();
		log();
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
		drivebase.log();
//		elevator.log();
//		collector.log();
		oi.log();
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
