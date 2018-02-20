package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command assumes the robot is at the Mid Right position.
 * It drives forward and scores the cube on the switch.
 */
public class MidRightSwitch extends CommandGroup {
	// The distance the robot must move to go from having its back flush with the wall
	// to having its front flush with the switch.
	private static final double INCHES_FROM_WALL_TO_SWITCH_NEAR_WALL = 101.6; // Measured in Solidworks, 2018-2-15

	public MidRightSwitch() {
		// Robot is on the correct side, score the cube after reaching
		// the auto line.
		addSequential(new DriveStraight(INCHES_FROM_WALL_TO_SWITCH_NEAR_WALL
				- Constants.AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
		addSequential(new ScoreStartingCubeOnSwitch());

	}
}
