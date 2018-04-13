package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchThenScale extends CommandGroup
{
	// These strategies assumes we have a cube pre-loaded on the robot.
		// GENERAL LOGIC:
		private static final double ONE_FOOT = 12.0;
		private static final double AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES = 0; // 2; // During auto moves to score on the
																				// switch, move up this close to the switch
																				// wall.

		// IF LEFT LOGIC:
		private static final double L_FORMAT_PATTERN = 0.0;
		private static final double L_INITIAL_MOVE = 148.19;
		private static final double L_TO_R_MOVE = 158.5;
		private static final double L_TO_SWITCH_MOVE = 156;
		private static final double L_FINAL_MOVE = 19.56 + 10; // Adjustment made based on Doppler testing 4/10/18
		public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

		// IF CENTER LOGIC:
		private static final double C_FORMAT_PATTERN = 0.0;
		private static final double C_INITAL_MOVE = 48.0 - 18.0;
		private static final double C_R_DISTANCE_IN_THE_MIDDLE = 48.0 + 3.0;
		private static final double C__R_FINAL_MOVE = 48.0 + 18.0 + 18.0 + 1.0;
		private static final double C_L_DISTANCE_IN_THE_MIDDLE = 48.0 + 15.0;
		private static final double C__L_FINAL_MOVE = 48.0 + 18.0 + 18.0 + 1.0;
		private static final double C_2_CUBE_LINEUP = 18.0;
		private static final double C_2_CUBE_CLOSEUP = 18.0;
		private static final double C_2_SCALE_INITIAL_MOVE = 76.75;
		private static final double C_2_SCALE_FINAL_MOVE = 241.31;
		private static final double C_2_SCALE_ACROSS_MOVE = 74;
		private static final double C_2_ACROSS_SCALE_FINAL_MOVE = 230.25;
		public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";

		// IF RIGHT LOGIC:
		private static final double R_FORMAT_PATTERN = 0.0;
		private static final double R_INITAL_MOVE = 148.19;
		private static final double R_FINAL_MOVE = 19.56 + 10; // Adjustment made based on Doppler testing of left side on 4/10/18
		private static final double R_TO_L_INITIAL_MOVE = 12.0;
		private static final double R_TO_L_MOVE = 158.5;
		private static final double R_TO_SWITCH_MOVE = 156;
		public static final String R_DESCRIPTION = "Go to switch hot side from right position and score";

		public SwitchThenScale(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
				StartPosition robotStartingPosition)
		{
			// LEFT SIDE MOVE:
			if (robotStartingPosition == StartPosition.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(L_INITIAL_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(L_FINAL_MOVE - AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.LEFT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(ONE_FOOT, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(L_TO_R_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(L_TO_SWITCH_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}

			/* ====================================== */
			// CENTER MOVE:
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
			{
				//Switch attack portion
				addSequential(new DriveStraightLockedGears(C_INITAL_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_L_DISTANCE_IN_THE_MIDDLE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C__L_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
				
				//Scale attack portion
				addSequential(new DriveStraightLockedGears(-C_2_CUBE_LINEUP, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_2_CUBE_CLOSEUP, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-C_2_SCALE_INITIAL_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_2_SCALE_FINAL_MOVE, true));
				addSequential(new Pivot(65));
				addSequential(new ScoreStartingCubeOnScale());
			}
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
			{
				//Switch attack portion
				addSequential(new DriveStraightLockedGears(C_INITAL_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_L_DISTANCE_IN_THE_MIDDLE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C__L_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
				
				//Scale attack portion
				addSequential(new DriveStraightLockedGears(-C_2_CUBE_LINEUP, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_2_CUBE_CLOSEUP, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-C_2_SCALE_INITIAL_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_2_SCALE_ACROSS_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_2_ACROSS_SCALE_FINAL_MOVE, true));
				addSequential(new Pivot(-90));
				addSequential(new ScoreStartingCubeOnScale());
			}
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
			{
				//Switch attack portion
				addSequential(new DriveStraightLockedGears(C_INITAL_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_R_DISTANCE_IN_THE_MIDDLE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C__R_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
				
				//Scale attack portion
				addSequential(new DriveStraightLockedGears(-C_2_CUBE_LINEUP, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_2_CUBE_CLOSEUP, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-C_2_SCALE_INITIAL_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_2_SCALE_ACROSS_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_2_ACROSS_SCALE_FINAL_MOVE, true));
				addSequential(new Pivot(90));
				addSequential(new ScoreStartingCubeOnScale());
			}
			else if (robotStartingPosition == StartPosition.CENTER
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT
					&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
			{
				//Switch attack portion
				addSequential(new DriveStraightLockedGears(C_INITAL_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_R_DISTANCE_IN_THE_MIDDLE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C__R_FINAL_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
				
				//Scale attack portion
				addSequential(new DriveStraightLockedGears(-C_2_CUBE_LINEUP, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(C_2_CUBE_CLOSEUP, false));
				addSequential(new AutoPickUpCubeWithDrive());
				addSequential(new DriveStraightLockedGears(-C_2_SCALE_INITIAL_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(C_2_SCALE_FINAL_MOVE, true));
				addSequential(new Pivot(-65));
				addSequential(new ScoreStartingCubeOnScale());
			}

			/* ====================================== */
			// RIGHT SIDE MOVE:
			else if (robotStartingPosition == StartPosition.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
			{
				addSequential(new DriveStraightLockedGears(R_TO_L_INITIAL_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(R_TO_L_MOVE, false));
				addSequential(new Pivot(90));
				addSequential(new DriveStraightLockedGears(R_TO_SWITCH_MOVE, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}
			else if (robotStartingPosition == StartPosition.RIGHT
					&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
			{
				addSequential(new DriveStraightLockedGears(R_INITAL_MOVE, false));
				addSequential(new Pivot(-90));
				addSequential(new DriveStraightLockedGears(R_FINAL_MOVE - AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES, false));
				addSequential(new ScoreStartingCubeOnSwitch());
			}

			/* ====================================== */
			// NO GAME DATA:
			else
			{
				System.out.println("NO GAME DATA FOUND");
				addSequential(new AnyForward());
			}
		}
}