package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchAttack extends CommandGroup {

	// These strategies assumes we have a cube pre-loaded on the robot.

	// IF LEFT LOGIC:
	private static final double L_FORMAT_PATTERN = 0.0;
	private static final double L_DISTANCE_UNTIL_TURN = 167.87;
	private static final double L_DEGREES_TO_ROTATE_AFTER_D1 = 90.0;
	private static final double L_RADIUS_OF_SNUG_CIRCLE = 14.37;
	private static final double L_DISTANCE_UNTIL_FINAL = 25.0;
	public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

	// IF CENTER LOGIC:
	private static final double C_FORMAT_PATTERN = 0.0;
	public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";

	// IF RIGHT LOGIC:
	private static final double R_FORMAT_PATTERN = 0.0;
	public static final String R_DESCRIPTION = "Go to switch hot side from right position and score";

	public SwitchAttack(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
			StartPosition robotStartingPosition) {
		
		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {

			addSequential(new DriveStraight(L_DISTANCE_UNTIL_TURN));
			//addSequential(new SweepTurn(L_DEGREES_TO_ROTATE_AFTER_D1, L_RADIUS_OF_SNUG_CIRCLE));
			addSequential(new Pivot(L_DEGREES_TO_ROTATE_AFTER_D1));
			addSequential(new DriveStraight(L_DISTANCE_UNTIL_FINAL));
			addSequential(new ElevateCubeAndScore(ElevatorHoldPoint.SWITCH_SCORE));
			addSequential(new ResetElevatorAndWrist());
			
		} else if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

		}

		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {

		} else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

		}

		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {

		} else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

		}

		// NO GAME DATA:
		else {
			addSequential(new AnyForward());
		}
	}

}
