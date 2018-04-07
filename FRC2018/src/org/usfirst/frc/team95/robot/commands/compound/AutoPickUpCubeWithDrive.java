package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.collector.AutoCloseMawOnCube;
import org.usfirst.frc.team95.robot.commands.collector.OpenMaw;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.TimedIngestCube;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveAtThrottle;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoPickUpCubeWithDrive extends CommandGroup {
	private static final double FORWARD_THROTTLE = 0.25;

	public AutoPickUpCubeWithDrive() {
		addSequential(new SetWristAngle(WristAngle.DOWN));
		addParallel(new DriveAtThrottle(FORWARD_THROTTLE));
		addSequential(new OpenMaw());
		addSequential(new DriveStraight(12 * 4.5));
		addSequential(new Pause(0.25));
		addSequential(new AutoCloseMawOnCube()); // This one waits until the cube is detected
		//addParallel(new DriveAtThrottle(0)); // This only exists to cancel the last DriveAtThrottle.  It will remain active until another move takes over the drivebase.
		addSequential(new SetWristAngle(WristAngle.MID_UP));
		addSequential(new TimedIngestCube());
	}
}
