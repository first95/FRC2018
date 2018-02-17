package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.collector.AutoCloseMawOnCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.TimedIngestCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 *
 */
public class AutoPickUpCubeManualDrive extends CommandGroup {
	public AutoPickUpCubeManualDrive() {
		addSequential(new SetWristAngle(WristAngle.DOWN));
		addSequential(new AutoCloseMawOnCube()); // This one waits until the cube is detected
		addSequential(new TimedIngestCube());
		addSequential(new SetWristAngle(WristAngle.MID_UP));
	}
}
