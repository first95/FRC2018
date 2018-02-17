package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.commands.collector.AutoCloseMawOnCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.TimedIngestCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

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
		addSequential(new Nothing());// Without this, holding the button will restart the move after it completes
	}
}
