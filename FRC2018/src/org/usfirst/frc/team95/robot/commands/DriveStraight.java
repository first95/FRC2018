/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.subsystems.DriveBase;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class DriveStraight extends Command {
//	private PIDController pid;
	double distanceInches;
	
	public DriveStraight(double distance) {
		requires(Robot.drivebase);
		
		this.distanceInches = distance;
		
//		pid = new PIDController(4, 0, 0, new PIDSource() {
//			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
//
//			@Override
//			public double pidGet() {
//				return Robot.drivebase.getDistance();
//			}
//
//			@Override
//			public void setPIDSourceType(PIDSourceType pidSource) {
//				m_sourceType = pidSource;
//			}
//
//			@Override
//			public PIDSourceType getPIDSourceType() {
//				return m_sourceType;
//			}
//		}, new PIDOutput() {
//			@Override
//			public void pidWrite(double d) {
//				Robot.drivebase.drive(d, d);
//			}
//		});
//		pid.setAbsoluteTolerance(0.01);
//		pid.setSetpoint(distance);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		// Get everything in a safe starting state.
		Robot.drivebase.reset();
		
		// Command the movement
		Robot.drivebase.travelStraight(distanceInches);
		
//		pid.reset();
//		pid.enable();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
//		return pid.onTarget();
		return Robot.drivebase.onTarget();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// Stop PID and the wheels
//		pid.disable();
		Robot.drivebase.drive(0, 0);
	}
}
