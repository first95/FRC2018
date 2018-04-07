package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.LockGear;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAttack extends CommandGroup {

	// These strategies assumes we have a cube pre-loaded on the robot.

	// INDEPENDENT CONSTANTS:
	private static final double ONE_FOOT = 12.0;

	// IF LEFT LOGIC:
	private static final double L_FORMAT_PATTERN = 0.0;
	private static final double L_INITAL_MOVE = 304.31 + 10.0;
	private static final double L_TO_R_INITIAL_MOVE = 231.0;
	private static final double L_TO_R_ACROSS_MOVE = 264.0;
	private static final double L_TO_R_NULL_ZONE = 33.0;
	private static final double L_TO_R_FINAL_MOVE = 80.0;
	public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

	// IF CENTER LOGIC:
	private static final double C_FORMAT_PATTERN = 0.0;
	private static final double C_L_INITIAL_MOVE = 12.0;
	private static final double C_L_MIDDLE_MOVE = 140.0;
	private static final double C_L_FINAL_MOVE = 302.31;
	private static final double C_R_INITIAL_MOVE = 12.0;
	private static final double C_R_MIDDLE_MOVE = 184.0;
	private static final double C_R_FINAL_MOVE = 302.31;
	public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";

	// IF RIGHT LOGIC:
	private static final double R_FORMAT_PATTERN = 0.0;
	private static final double R_INITAL_MOVE = 304.31 + 10.0;;
	public static final String R_DESCRIPTION = "Go to switch hot side from right position and score";

	public ScaleAttack(FieldSide whichSideOfTheScaleIsOurColor, StartPosition robotStartingPosition) {

		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraightLockedGears(L_INITAL_MOVE - 30 - 12, true));
			addSequential(new Pivot(45));
			addSequential(new ScoreStartingCubeOnScale());
		} else if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, true));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, true));
			addSequential(new ScoreStartingCubeOnScale());
		}

		/* ====================================== */
		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraightLockedGears(C_L_INITIAL_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraightLockedGears(C_L_MIDDLE_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(C_L_FINAL_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
		} else if (robotStartingPosition == StartPosition.CENTER && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraightLockedGears(C_R_INITIAL_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(C_R_MIDDLE_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraightLockedGears(C_R_FINAL_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
		}

		/* ====================================== */
		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, true));
			addSequential(new ScoreStartingCubeOnScale());
		} else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraightLockedGears(R_INITAL_MOVE - 30 - 12, true));
			addSequential(new Pivot(-45));
			addSequential(new ScoreStartingCubeOnScale());
		}

		/* ====================================== */
		// NO GAME DATA:
		else {
			addSequential(new AnyForward());
		}
	}

}
