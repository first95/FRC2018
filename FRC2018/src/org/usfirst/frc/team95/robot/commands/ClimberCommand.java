package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team95.robot.Constants;

public class ClimberCommand extends Command
{
	public ClimberCommand()
	{
		requires(Robot.climber);
	}
	
	protected void execute()
	{
		double input = Constants.weaponStick.getRawAxis(Constants.CLIMBER_AXIS);
		Robot.climber.climb(input);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
