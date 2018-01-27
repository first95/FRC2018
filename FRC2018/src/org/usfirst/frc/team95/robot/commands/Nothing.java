package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Nothing extends Command
	{

	@Override
	protected boolean isFinished()
		{
			// TODO Auto-generated method stub
			System.out.println("Do nothing!");
			return false;
		}


	}
