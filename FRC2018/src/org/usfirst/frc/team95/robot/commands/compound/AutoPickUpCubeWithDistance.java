package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.collector.AutoCloseMawOnCube;
import org.usfirst.frc.team95.robot.commands.collector.CloseMaw;
import org.usfirst.frc.team95.robot.commands.collector.OpenMaw;
import org.usfirst.frc.team95.robot.commands.collector.RunChains;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.TimedIngestCube;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveAtThrottle;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPickUpCubeWithDistance extends CommandGroup {

	public AutoPickUpCubeWithDistance(double distanceToDrive) {
		addSequential(new SetWristAngle(WristAngle.DOWN));
		addParallel(new OpenMaw());
		addSequential(new Pause(0.25));
		addSequential(new DriveStraightLockedGears(distanceToDrive, false));
		addParallel(new RunChains(1.0));
		addSequential(new Pause(0.25));
		addSequential(new CloseMaw());
		addSequential(new SetWristAngle(WristAngle.MID_UP));
		addSequential(new TimedIngestCube());
	}
}
