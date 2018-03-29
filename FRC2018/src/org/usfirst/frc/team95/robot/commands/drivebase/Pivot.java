/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team95.robot.commands.drivebase;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Robot;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class Pivot extends Command {
	double degreesCw;
	double robotHeadingAtStartOfMove;
	
	private static final double K_P = 1.75;
	private static final double K_I = 0.0;
	private static final double K_D = 0.0;
	private static final double PIVOT_SPEED = 48;
	private static final double END_STOP_TOLERANCE = 2;
	
	private PIDController clp;
	
	public Pivot(double degreesCw) {
		
		// We have to do this to make it turn the right way : John says "Cheese Warning"
		degreesCw *= -1;
		
		requires(Robot.drivebase);
		
		clp = new PIDController(K_P, K_I, K_D, new PIDSource() {
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
			
			}
			
			@Override
			public double pidGet() {
				return Robot.drivebase.getRobotHeadingDegrees();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		}, new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Robot.drivebase.setPivotRate(-output);
			}
		}, 0.01);
		
		clp.setOutputRange(-PIVOT_SPEED, PIVOT_SPEED);
		clp.setAbsoluteTolerance(END_STOP_TOLERANCE);
		
		//SmartDashboard.putData("PIDController", clp);
		//SmartDashboard.putNumber("PIDController F Value", clp.getF());
		
		//SmartDashboard.putNumber("target pos", clp.getSetpoint());
		//SmartDashboard.putNumber("Error", clp.getError());
		
		this.degreesCw = degreesCw;
	}

	@Override
	protected void execute() {
		//SmartDashboard.putNumber("target pos", clp.getSetpoint());
		//SmartDashboard.putNumber("Error", clp.getError());
	}
	
	// Called every time the command starts
	@Override
	public void initialize() {
		System.out.println("Starting Pivot (" + degreesCw + " degrees)");
		robotHeadingAtStartOfMove = Robot.drivebase.getRobotHeadingDegrees();
		
		// Command the movement
		clp.enable();
		clp.setSetpoint((robotHeadingAtStartOfMove + degreesCw));
		
		//Robot.drivebase.pivotDegreesClockwise(degreesCw);
		//Robot.drivebase.setPivotRate(degreesCw > 0 ? 36 : -36);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return clp.onTarget();
		// Heading is measured with positive headings being counter-clockwise
//		if(degreesCw > 0) {
//			double headingDegrees = Robot.drivebase.getRobotHeadingDegrees();
//			double lessThanValueToCheck = robotHeadingAtStartOfMove - (degreesCw - TURN_DEGREE_CORRECTION);
//			//System.out.println("Heading Degree " + headingDegrees);
//			//System.out.println("Less Than Value " + lessThanValueToCheck);
//			return (headingDegrees < (lessThanValueToCheck));
//		} else if (degreesCw < 0) {
//			double headingDegrees = Robot.drivebase.getRobotHeadingDegrees();
//			double greaterThanValueToCheck = robotHeadingAtStartOfMove - (degreesCw + TURN_DEGREE_CORRECTION);
//			//System.out.println("Heading Degree " + headingDegrees);
//			//System.out.println("Greater Than Value " + greaterThanValueToCheck);
//			return (headingDegrees > (greaterThanValueToCheck));
//		} else { // degreesCW == 0
//			return true;
//		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		clp.disable();
		System.out.println("Ending Pivot (" + degreesCw + " degrees)");
		Robot.drivebase.drive(0, 0);
	}
}
