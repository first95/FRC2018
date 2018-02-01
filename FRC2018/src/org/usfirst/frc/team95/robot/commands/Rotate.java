package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


public class Rotate extends Command
	{

	public Rotate(double degree, double speed) {
		Robot.drivebase.pivotDegrees(degree, speed);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	}