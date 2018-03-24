package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.LockGear;
import org.usfirst.frc.team95.robot.commands.drivebase.UnlockGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveStraightLockedGears extends CommandGroup{
	
	public DriveStraightLockedGears(double inchesToTravel, boolean isHighGear) {
		addSequential(new LockGear(isHighGear));
		addSequential(new DriveStraight(inchesToTravel));
		addSequential(new UnlockGear());
	}
	
}
