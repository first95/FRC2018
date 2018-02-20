package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command assumes the robot is at the far Left or Right position.
 * (indicate which one with the constructor parameter)
 * It drives forward, pivots, and scores the cube on the switch.
 */
public class LeftOrRightSwitch extends CommandGroup {
	// The distance the robot must move to go from having its back flush with the wall
	// to being in the middle of the switch.
	private static final double INCHES_FROM_WALL_TO_SWITCH_MIDDLE = 148.19; // Measured in Solidworks, 2018-2-15
	// The second leg when we need to score from the far left or right of the field
	private static final double INCHES_FROM_DRIVE_TO_SWITCH_SIDE = 19.56; // Measured in Solidworks, 2018-2-15

	public LeftOrRightSwitch(FieldSide whichSide) {
		// Robot is on the correct side, score the cube after reaching
		// the auto line.
		addSequential(new DriveStraight(INCHES_FROM_WALL_TO_SWITCH_MIDDLE));
		
		if(whichSide == FieldSide.LEFT)
		{
			addSequential(new Pivot(90)); // Clockwise, "turn to the right"
		}
		else
		{ // Must be right
			addSequential(new Pivot(-90)); // Counterclockwise, "turn to the left"
		}
		addSequential(new DriveStraight(INCHES_FROM_DRIVE_TO_SWITCH_SIDE
				- Constants.AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
		addSequential(new ScoreStartingCubeOnSwitch());
	}
}
