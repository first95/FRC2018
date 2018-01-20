
package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.subsystems.BareMinimumMotorSubsystem;
import org.usfirst.frc.team95.robot.subsystems.BareMinimumPneumaticSubsystem;
import org.usfirst.frc.team95.robot.subsystems.Claw;
import org.usfirst.frc.team95.robot.subsystems.Climber;
import org.usfirst.frc.team95.robot.subsystems.DriveBase;
import org.usfirst.frc.team95.robot.subsystems.Elevator;
//import org.usfirst.frc.team95.robot.subsystems.FacePusher;
import org.usfirst.frc.team95.robot.subsystems.Wrist;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot
{

	Command autonomousCommand;
	SendableChooser chooser;
	SendableChooser a, b, c;
	
	// Actual classes used in the robot
	public static DriveBase drivebase;
	public static OI oi;
	public static Climber climber;
	//public static FacePusher facepusher;

	// Examples - the minimum possible subsystems
	public static BareMinimumPneumaticSubsystem bmns;
	public static BareMinimumMotorSubsystem bmms;
	
	// Slightly more elaborate example subsystems from WPILib
	public static Elevator elevator;
	public static Wrist wrist;
	public static Claw claw;
	boolean once = true;

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
	 */
	@Override
	public void robotInit()
		{

			// Initialize all subsystems
			drivebase = new DriveBase();
			elevator = new Elevator();
			wrist = new Wrist();
			claw = new Claw();
			oi = new OI();
			climber = new Climber();
			bmns = new BareMinimumPneumaticSubsystem();
			//facepusher = new FacePusher();
			Compressor compressor = new Compressor();
			
			// Show what command your subsystem is running on the SmartDashboard
			SmartDashboard.putData(drivebase);
			SmartDashboard.putData(elevator);
			SmartDashboard.putData(wrist);
			SmartDashboard.putData(claw);
			SmartDashboard.putData(climber);
			SmartDashboard.putData(bmns);
			//SmartDashboard.putData(facepusher);
				
			// Sendable Chooser
			chooser = new SendableChooser();
			SmartDashboard.putData("Auto mode", chooser);

			a = new SendableChooser();
			a.addDefault("None", new Nothing());

			// DISPLAY CHOSERS TO DASHBOARD:
			SmartDashboard.putData("1st", a);
			
			
			drivebase.brake(false);
			}

		@Override
		public void autonomousInit()
			{
				String gameData;
				gameData = DriverStation.getInstance().getGameSpecificMessage();
				System.out.println("Plate assignments are "+gameData);
//				if(gameData.charAt(0) == 'L')
//				{
//					//Put left auto code here
//				} else {
//					//Put right auto code here
//				}
//				drivebase.brake(true);
//				
//				// instantiate the command used for the autonomous period
//				autonomousCommand = (Command) a.getSelected();
//				autonomousCommand.start();
			}

		/**
		 * This function is called periodically during autonomous
		 */
		@Override
		public void autonomousPeriodic()
			{
//				Scheduler.getInstance().run();
				log();
			}

		/**
		 * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem
		 * information you want to clear when the robot is disabled.
		 */
		public void disabledInit()
			{
				drivebase.brake(false);
			}

		public void disabledPeriodic()
			{
				Scheduler.getInstance().run();
			}
		
		
		@Override
		public void teleopInit()
			{
				drivebase.brake(true);
				// This makes sure that the autonomous stops running when
				// teleop starts running. If you want the autonomous to
				// continue until interrupted by another command, remove
				// this line or comment it out.
				autonomousCommand.cancel();
			}

		/**
		 * This function is called periodically during operator control
		 */
		@Override
		public void teleopPeriodic()
			{
				Scheduler.getInstance().run();
				log();
			}

		/**
		 * This function is called periodically during test mode
		 */
		@Override
		public void testPeriodic()
			{
				LiveWindow.run();
			}

		/**
		 * The log method puts interesting information to the SmartDashboard.
		 */
		private void log()
			{
				wrist.log();
				elevator.log();
				drivebase.log();
				claw.log();
				climber.log();
				//facepusher.log();
			}
	}
