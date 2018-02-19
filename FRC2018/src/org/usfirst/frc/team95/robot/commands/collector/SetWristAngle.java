package org.usfirst.frc.team95.robot.commands.collector;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class SetWristAngle extends TimedCommand {
	public static double CYLINDER_ACTUATION_DURATION_S = 0.25; // Amount of time to wait for the air cylinders to move the wrist
	
	public enum WristAngle {
		UP,
		MID_UP,
		MID_DOWN,
		DOWN,
	};
	
	WristAngle targetAngle;
	
	public SetWristAngle(WristAngle angle) {
		super(CYLINDER_ACTUATION_DURATION_S);
		requires(Robot.collector);
		targetAngle = angle;
	}
	
	@Override
	public synchronized void initialize() {
		// Apply the appropriate states to the wrist cylinders
		boolean stage1r = false, stage2r = false;
		
		switch(targetAngle) {
		case UP:
			stage1r = false; stage2r = false;
			break;
		case MID_UP:
			stage1r = false; stage2r = true;
			break;
		case MID_DOWN:
			stage1r = true;  stage2r = false;
			break;
		case DOWN:
			stage1r = true;  stage2r = true;
			break;
		default:
			break;
		}
		if(Robot.collector.getWristStageOneRetracted() == stage1r && 
				Robot.collector.getWristStageTwoRetracted() == stage2r) {
			// We're already at the commanded wrist angle, don't do anything
			setTimeout(0);
		} else {
			Robot.collector.setWristStageOneRetracted(stage1r);
			Robot.collector.setWristStageTwoRetracted(stage2r);
			Robot.oi.setWristStageOneRectractedStatus(stage1r);
			Robot.oi.setWristStageTwoRetractedStatus(stage2r);
		}
	}
}
