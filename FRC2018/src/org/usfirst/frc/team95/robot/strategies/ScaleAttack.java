package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.compound.ScoreStartingCubeOnScale;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;

public class ScaleAttack extends Strategy {
	public ScaleAttack() {
		// all of the instructions for this auto move are in AdjustStrategy
	}

	// These strategies assumes we have a cube pre-loaded on the robot.

	// IF LEFT LOGIC: (VALUES NEED TO BE CALCULATED!!!)
	private static final double L_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION = 12;
	// This is the distance to travel between the two sweeping turns
	private static final double L_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 133.46;
	private static final double L_INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION = 155.86;
	private static final double L_DEGREES_FOR_SWEEP_TURN = 48.55;
	private static final double L_INCHES_FOR_SWEEP_TURN_RADIUS = 32.0;
	public static final String L_DESCRIPTION = "Go to scale hot side from left position and score";

	// IF CENTER LOGIC:
	private static final double C_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION = 12;
	// This is the distance to travel between the two sweeping turns
	private static final double C_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 133.46;
	private static final double C_INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION = 155.86;
	private static final double C_DEGREES_FOR_SWEEP_TURN = 48.55;
	private static final double C_INCHES_FOR_SWEEP_TURN_RADIUS = 32.0;
	public static final String C_DESCRIPTION = "Go to scale hot side from center position and score";

	// IF RIGHT LOGIC: (VALUES NEED TO BE CALCULATED!!!)
	private static final double R_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION = 12;
	// This is the distance to travel between the two sweeping turns
	private static final double R_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 133.46;
	private static final double R_INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION = 155.86;
	private static final double R_DEGREES_FOR_SWEEP_TURN = 48.55;
	private static final double R_INCHES_FOR_SWEEP_TURN_RADIUS = 32.0;
	public static final String R_DESCRIPTION = "Go to scale hot side from right position and score";

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
			StartPosition robotStartingPosition) {

		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

		} else if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {

		}

		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(-C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new ScoreStartingCubeOnScale());
		} else if (robotStartingPosition == StartPosition.CENTER && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(-C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BEFORE_FINAL_ROTATION));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(C_INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new ScoreStartingCubeOnScale());
		}

		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

		} else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {

		}

		// NO GAME DATA:
		else {
			addSequential(new AnyForward());
		}
	}
}
