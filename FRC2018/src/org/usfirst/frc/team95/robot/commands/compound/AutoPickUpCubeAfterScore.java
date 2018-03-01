package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.commands.collector.AutoCloseMawOnCube;
import org.usfirst.frc.team95.robot.commands.collector.RunChains;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.TimedIngestCube;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPickUpCubeAfterScore extends CommandGroup
{
	
	public AutoPickUpCubeAfterScore(double driveIntoCube)
	{
		addSequential(new RunChains(AutoCloseMawOnCube.INTAKE_THROTTLE)); // Start the chains running before we start moving the pneumatics
		addSequential(new SetWristAngle(WristAngle.DOWN));
		addSequential(new AutoCloseMawOnCube()); // This one waits until the cube is detected
		addSequential(new DriveStraight(driveIntoCube));
		addSequential(new TimedIngestCube()); // This will stop the chains once it's done
		addSequential(new SetWristAngle(WristAngle.MID_UP));
		addSequential(new DriveStraight(-driveIntoCube));
		addSequential(new Nothing());
	}
}
