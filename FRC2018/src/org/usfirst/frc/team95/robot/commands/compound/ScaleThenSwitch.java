package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleThenSwitch extends CommandGroup
{
	// These strategies assumes we have a cube pre-loaded on the robot.
	
		//INDEPENDENT CONSTANTS:
		private static final double ONE_FOOT = 12.0;
		
		private static final double SCALE_TO_SWITCH_INITIAL_MOVE = 52.84;
		private static final double SCALE_TO_SWITCH_NEW_CUBE = 80.59;
		private static final double SCALE_TO_SWITCH_FINAL_MOVE = 8.49;
		private static final double SCALE2_TO_SWITCH_INITIAL_MOVE = 84.81;
		private static final double SCALE2_TO_SWITCH_ACROSS_MOVE = 133.5;

		// IF LEFT LOGIC:
		private static final double L_FORMAT_PATTERN = 0.0;
		private static final double L_INITAL_MOVE = 304.31 + 10.0;
		private static final double L_TO_R_INITIAL_MOVE = 231.0;
		private static final double L_TO_R_ACROSS_MOVE = 264.0;
		private static final double L_TO_R_NULL_ZONE = 33.0;
		private static final double L_TO_R_FINAL_MOVE = 80.0;
		public static final String L_DESCRIPTION = "Go to switch hot side from left position and score, then pick up a new cube and score on switch hot side.";

		// IF MID-LEFT LOGIC:
		private static final double ML_FORMAT_PATTERN = 0.0;
		private static final double ML_INITIAL_MOVE = 48.0;
		private static final double ML_ACROSS_MOVE = 55.5;
		//private static final double ML_SWEEP_TURN_RADIUS = 162.29;
		//private static final double ML_TO_R_SWEEP_TURN_RADIUS = 34.5;
		//private static final double ML_SWEEP_TURN_DEGREE = 28.19;
		//private static final double ML_TO_R_SWEEP_TURN_DEGREE = 90.0;
		private static final double ML_FINAL_MOVE = 266.31;
		private static final double ML_TO_R_INITIAL_MOVE = 48.0;
		private static final double ML_TO_R_ACROSS_MOVE = 192.0;
		private static final double ML_TO_R_FINAL_MOVE = 266.31;
		public static final String ML_DESCRIPTION = "Go to switch hot side from mid-left position and score, then pick up a new cube and score on switch hot side.";

		// IF CENTER LOGIC:
		private static final double C_FORMAT_PATTERN = 0.0;
		private static final double C_L_INITIAL_MOVE = 12.0;
		//private static final double C_L_SWEEP_RADIUS = 48.0;
		//private static final double C_L_SWEEP_DEGREE = 54.34;
		private static final double C_L_MIDDLE_MOVE = 140.0;
		//private static final double C_L_SWEEP2_RADIUS = 82.74;
		//private static final double C_L_SWEEP2_DEGREE = 54.34;
		private static final double C_L_FINAL_MOVE = 302.31;
		
		private static final double C_R_INITIAL_MOVE = 12.0;
		//private static final double C_R_SWEEP_RADIUS = 48.0;
		//private static final double C_R_SWEEP_DEGREE = 54.34;
		private static final double C_R_MIDDLE_MOVE = 184.0;
		//private static final double C_R_SWEEP2_RADIUS = 56.0;
		//private static final double C_R_SWEEP2_DEGREE = 45.0;
		private static final double C_R_FINAL_MOVE = 302.31;
		public static final String C_DESCRIPTION = "Go to switch hot side from center position and score, then pick up a new cube and score on switch hot side.";

		// IF RIGHT LOGIC:
		private static final double R_FORMAT_PATTERN = 0.0;
		private static final double R_INITAL_MOVE = 304.31 + 10.0;;
		public static final String R_DESCRIPTION = "Go to switch hot side from right position and score, then pick up a new cube and score on switch hot side.";

		// IF MID-RIGHT LOGIC:
		private static final double MR_FORMAT_PATTERN = 0.0;
		private static final double MR_INITIAL_MOVE = 64.0;
		private static final double MR_ACROSS_MOVE = 140.0;
		//private static final double MR_SWEEP_RADIUS = 36.0;
		//private static final double MR_SWEEP_DEGREE = 45.0;
		private static final double MR_MIDDLE_DISTANCE = 56.77;
		//private static final double MR_SWEEP2_RADIUS = 36.0;
		//private static final double MR_SWEEP2_DEGREE = 45.0;
		private static final double MR_FINAL_MOVE = 167.01;
		private static final double MR_TO_L_INITIAL_MOVE = 64.0;
		private static final double MR_TO_L_ACROSS_MOVE = 182.9;
		private static final double MR_TO_L_NULL_ZONE = 140.0;
		private static final double MR_TO_L_ENDING_MOVE = 0.0;
		public static final String MR_DESCRIPTION = "Go to switch hot side from mid-right position and score, then pick up a new cube and score on switch hot side.";

		public ScaleThenSwitch(FieldSide whichSideOfTheScaleIsOurColor,
				FieldSide whichSideOfTheNearSwitchIsOurColor,
				StartPosition robotStartingPosition)
		{
			// Everything is calculated, but none of it has been tested.
			// LEFT SIDE MOVE:
			if (robotStartingPosition == StartPosition.LEFT
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(L_INITAL_MOVE, true));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(98));
				//addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_NEW_CUBE, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-12, false));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.LEFT
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(L_INITAL_MOVE, true));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(45));
				//addSequential(new DriveStraightLockedGears(SCALE2_TO_SWITCH_INITIAL_MOVE, false));
				//addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(-SCALE2_TO_SWITCH_ACROSS_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(12, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.LEFT
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, false));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(-L_TO_R_FINAL_MOVE, false));
				addSequential(new Pivot(-45));
				//addSequential(new DriveStraightLockedGears(SCALE2_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(-SCALE2_TO_SWITCH_ACROSS_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(12, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.LEFT
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, false));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(-L_TO_R_FINAL_MOVE, false));
				addSequential(new Pivot(-98));
				//addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_NEW_CUBE, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-12, false));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			
			/*======================================*/
			// CENTER MOVE:
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(C_L_INITIAL_MOVE, false));
				addSequential(new Pivot(-90));
				//addSequential(new SweepTurn(-C_L_SWEEP_DEGREE, C_L_SWEEP_RADIUS));
				addSequential(new DriveStraightLockedGears(C_L_MIDDLE_MOVE, false));
				addSequential(new Pivot(90));
				//addSequential(new SweepTurn(C_L_SWEEP2_DEGREE, C_L_SWEEP2_RADIUS));
				addSequential(new DriveStraightLockedGears(C_L_FINAL_MOVE, true));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(98));
				//addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_NEW_CUBE, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-12, false));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(C_L_INITIAL_MOVE, false));
				addSequential(new Pivot(-90));
				//addSequential(new SweepTurn(-C_L_SWEEP_DEGREE, C_L_SWEEP_RADIUS));
				addSequential(new DriveStraightLockedGears(C_L_MIDDLE_MOVE, false));
				addSequential(new Pivot(90));
				//addSequential(new SweepTurn(C_L_SWEEP2_DEGREE, C_L_SWEEP2_RADIUS));
				addSequential(new DriveStraightLockedGears(C_L_FINAL_MOVE, true));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(45));
				//addSequential(new DriveStraightLockedGears(SCALE2_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(-SCALE2_TO_SWITCH_ACROSS_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(12, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(C_R_INITIAL_MOVE, false));
				addSequential(new Pivot(90));
				//addSequential(new SweepTurn(C_R_SWEEP_DEGREE, C_R_SWEEP_RADIUS));
				addSequential(new DriveStraightLockedGears(C_R_MIDDLE_MOVE, false));
				addSequential(new Pivot(-90));
				//addSequential(new SweepTurn(-C_R_SWEEP2_DEGREE, C_R_SWEEP2_RADIUS));
				addSequential(new DriveStraightLockedGears(C_R_FINAL_MOVE, true));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(-45));
				//addSequential(new DriveStraightLockedGears(SCALE2_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(-SCALE2_TO_SWITCH_ACROSS_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(12, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(C_R_INITIAL_MOVE, false));
				addSequential(new Pivot(90));
				//addSequential(new SweepTurn(C_R_SWEEP_DEGREE, C_R_SWEEP_RADIUS));
				addSequential(new DriveStraightLockedGears(C_R_MIDDLE_MOVE, false));
				addSequential(new Pivot(-90));
				//addSequential(new SweepTurn(-C_R_SWEEP2_DEGREE, C_R_SWEEP2_RADIUS));
				addSequential(new DriveStraightLockedGears(C_R_FINAL_MOVE, true));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(-98));
				//addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_NEW_CUBE, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-12, false));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			/*======================================*/

			// RIGHT SIDE MOVE:
			else if (robotStartingPosition == StartPosition.RIGHT
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, false));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(-L_TO_R_FINAL_MOVE, false));
				addSequential(new Pivot(98));
				//addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_NEW_CUBE, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-12, false));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.RIGHT
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, false));
				addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, false));
				addSequential(new Pivot(45));
				//addSequential(new DriveStraightLockedGears(SCALE2_TO_SWITCH_INITIAL_MOVE, false));
				//addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(-SCALE2_TO_SWITCH_ACROSS_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(12, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.RIGHT
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(R_INITAL_MOVE, true));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(-45));
				//addSequential(new DriveStraightLockedGears(SCALE2_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(-SCALE2_TO_SWITCH_ACROSS_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(12, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.RIGHT
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(R_INITAL_MOVE, true));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new ScoreStartingCubeOnScale());
				addSequential(new DriveStraightLockedGears(0, false));
				addSequential(new Pivot(-98));
				//addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_INITIAL_MOVE, true));
				//addSequential(new Pivot(45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_NEW_CUBE, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-12, false));
				addSequential(new Pivot(-45));
				addSequential(new DriveStraightLockedGears(SCALE_TO_SWITCH_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
				
			}
			
			/*======================================*/
			// NO GAME DATA:
			else
			{
				addSequential(new AnyForward());
			}
		}
}