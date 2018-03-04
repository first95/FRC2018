package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAttack extends CommandGroup {

	// These strategies assumes we have a cube pre-loaded on the robot.

	// IF LEFT LOGIC:
	private static final double L_FORMAT_PATTERN = 0.0;
	private static final double L_INITAL_MOVE = 304.31 + 10.0;
	public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

	// IF MID-LEFT LOGIC:
	private static final double ML_FORMAT_PATTERN = 0.0;
	private static final double ML_INITAL_MOVE = 12.0;
	private static final double ML_SWEEP_TURN_RADIUS = 162.29;
	private static final double ML_SWEEP_TURN_DEGREE = 28.19;
	private static final double ML_FINAL_MOVE = 139.1;
	public static final String ML_DESCRIPTION = "Go to switch hot side from mid-left position and score";

	// IF CENTER LOGIC:
	private static final double C_FORMAT_PATTERN = 0.0;
	private static final double C_INITAL_MOVE = 12.0;
	private static final double C_L_SWEEP_RADIUS = 48.0;
	private static final double C_L_SWEEP_DEGREE = 54.34;
	private static final double C_L_MIDDLE_MOVE = 80.58;
	private static final double C_L_SWEEP2_RADIUS = 82.74;
	private static final double C_L_SWEEP2_DEGREE = 54.34;
	private static final double C_L_FINAL_MOVE = 139.1;
	
	private static final double C_R_SWEEP_RADIUS = 48.0;
	private static final double C_R_SWEEP_DEGREE = 54.34;
	private static final double C_R_MIDDLE_MOVE = 69.1;
	private static final double C_R_SWEEP2_RADIUS = 56.0;
	private static final double C_R_SWEEP2_DEGREE = 45.0;
	private static final double C_R_FINAL_MOVE = 167.01;
	public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";

	// IF RIGHT LOGIC:
	private static final double R_FORMAT_PATTERN = 0.0;
	private static final double R_INITAL_MOVE = 303.71;
	public static final String R_DESCRIPTION = "Go to switch hot side from right position and score";

	// IF MID-RIGHT LOGIC:
	private static final double MR_FORMAT_PATTERN = 0.0;
	private static final double MR_INITAL_MOVE = 12.0;
	private static final double MR_SWEEP_RADIUS = 36.0;
	private static final double MR_SWEEP_DEGREE = 45.0;
	private static final double MR_MIDDLE_DISTANCE = 56.77;
	private static final double MR_SWEEP2_RADIUS = 36.0;
	private static final double MR_SWEEP2_DEGREE = 45.0;
	private static final double MR_FINAL_MOVE = 167.01;
	public static final String MR_DESCRIPTION = "Go to switch hot side from mid-right position and score";

	public ScaleAttack(FieldSide whichSideOfTheScaleIsOurColor,
			StartPosition robotStartingPosition) {

		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

			addSequential(new DriveStraight(L_INITAL_MOVE));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
			
		} else if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {

			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");
			
		}
		/*======================================*/

		// MID-LEFT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.MID_LEFT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

			addSequential(new DriveStraight(ML_INITAL_MOVE));
			addSequential(new SweepTurn(-ML_SWEEP_TURN_DEGREE, ML_SWEEP_TURN_RADIUS));
			addSequential(new SweepTurn(ML_SWEEP_TURN_DEGREE, ML_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(ML_FINAL_MOVE));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());

		} else if (robotStartingPosition == StartPosition.MID_LEFT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			
			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");

		}
		/*======================================*/

		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			
			addSequential(new DriveStraight(C_INITAL_MOVE));
			addSequential(new SweepTurn(-C_L_SWEEP_DEGREE, C_L_SWEEP_RADIUS));
			addSequential(new DriveStraight(C_L_MIDDLE_MOVE));
			addSequential(new SweepTurn(C_L_SWEEP2_DEGREE, C_L_SWEEP2_RADIUS));
			addSequential(new DriveStraight(C_L_FINAL_MOVE));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());

		} else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(C_INITAL_MOVE));
			addSequential(new SweepTurn(C_R_SWEEP_DEGREE, C_R_SWEEP_RADIUS));
			addSequential(new DriveStraight(C_R_MIDDLE_MOVE));
			addSequential(new SweepTurn(-C_R_SWEEP2_DEGREE, C_R_SWEEP2_RADIUS));
			addSequential(new DriveStraight(C_R_FINAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
			
		}
		/*======================================*/

		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");
			
		} else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(R_INITAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
			
		}
		/*======================================*/

		// MID-RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.MID_RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");
			
		} else if (robotStartingPosition == StartPosition.MID_RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(MR_INITAL_MOVE));
			addSequential(new SweepTurn(MR_SWEEP_DEGREE, MR_SWEEP_RADIUS));
			addSequential(new SweepTurn(-MR_SWEEP_DEGREE, MR_SWEEP_RADIUS));
			addSequential(new DriveStraight(MR_FINAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
			
		}
		/*======================================*/

		// NO GAME DATA:
		else {
			addSequential(new AnyForward());
		}
	}

}
