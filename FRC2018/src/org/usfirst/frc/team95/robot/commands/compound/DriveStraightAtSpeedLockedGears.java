package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.LockGear;
import org.usfirst.frc.team95.robot.commands.drivebase.SetMaxSpeed;
import org.usfirst.frc.team95.robot.commands.drivebase.UnlockGear;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveStraightAtSpeedLockedGears extends CommandGroup{
	
	public DriveStraightAtSpeedLockedGears(double inchesToTravel, boolean isHighGear, double maxSpeed) {
		addSequential(new LockGear(isHighGear));
		addSequential(new SetMaxSpeed(maxSpeed));
		addSequential(new DriveStraight(inchesToTravel));
		addSequential(new UnlockGear());
		addSequential(new SetMaxSpeed(1));
	}
	
}
