
package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.IOException;

import org.usfirst.frc.team95.robot.commands.Autonomous;
import org.usfirst.frc.team95.robot.components.ButtonTracker;
import org.usfirst.frc.team95.robot.components.SystemLogger;
import org.usfirst.frc.team95.robot.subsystems.BareMinimumMotorSubsystem;
import org.usfirst.frc.team95.robot.subsystems.BareMinimumPneumaticSubsystem;
import org.usfirst.frc.team95.robot.subsystems.Claw;
import org.usfirst.frc.team95.robot.subsystems.DriveBase;
import org.usfirst.frc.team95.robot.subsystems.Elevator;
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

		// Actual classes used in the robot
		public static DriveBase drivebase;
		public static OI oi;

		// Examples - the minimum possible subsystems
		public static BareMinimumPneumaticSubsystem bmns;
		public static BareMinimumMotorSubsystem bmms;
		
		// Slightly more elaborate example subsystems from WPILib
		public static Elevator elevator;
		public static Wrist wrist;
		public static Claw claw;
		public static SystemLogger sL;
		boolean once = true;

		/**
		 * This function is run when the robot is first started up and should be used
		 * for any initialization code.
		 */
		@Override
		public void robotInit()
			{

				// SystemLogger setup + TestWrite
				try
					{
						sL = new SystemLogger();
					}
				catch (IOException e)
					{	
						e.printStackTrace();
					}
				sL.SystemLoggerWrite("--Robot Boot");
				
				// Initialize all subsystems
				drivebase = new DriveBase();
				elevator = new Elevator();
				wrist = new Wrist();
				claw = new Claw();
				oi = new OI();
				sL.SystemLoggerWrite("Subsystems initialized");

				Compressor compressor = new Compressor();
				sL.SystemLoggerWrite("Compressor initialized");

				// instantiate the command used for the autonomous period
				autonomousCommand = new Autonomous();
				
				// Show what command your subsystem is running on the SmartDashboard
				SmartDashboard.putData(drivebase);
				SmartDashboard.putData(elevator);
				SmartDashboard.putData(wrist);
				SmartDashboard.putData(claw);
				
				drivebase.brake(false);
			}

		@Override
		public void autonomousInit()
			{
				drivebase.brake(true);
				autonomousCommand.start(); // schedule the autonomous command (example)
			}

		/**
		 * This function is called periodically during autonomous
		 */
		@Override
		public void autonomousPeriodic()
			{
				Scheduler.getInstance().run();
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
			}
	}
