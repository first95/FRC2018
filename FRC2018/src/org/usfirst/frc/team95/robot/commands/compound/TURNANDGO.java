package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.LockGear;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TURNANDGO extends CommandGroup{
	
	public TURNANDGO() {
		addSequential(new LockGear(false));
		addSequential(new Pivot(90));
		addSequential(new Pause(1));
		addSequential(new DriveStraight(12 * 2));
		// TODO Auto-generated constructor stub
	}

}
