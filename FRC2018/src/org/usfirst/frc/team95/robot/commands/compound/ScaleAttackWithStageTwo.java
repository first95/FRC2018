package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAttackWithStageTwo extends CommandGroup {

	// These strategies assumes we have a cube pre-loaded on the robot.
	
	// GENERAL LOGIC:
	private static final double RE_CENTER_DISTANCE = 2.0;
	private static final double DISTANCE_TILL_CENTER = (299.65-196.00);
	private static final double DISTANCE_TILL_SECOND_CUBE = ((264/2)-24);
	private static final double DISTANCE_TO_GRAB_CUBE = 5.0;
	private static final double ONE_FOOT = 12.0;
	private static final double BACKUP_DISTENCE_MOVE = 4.0;
	private static final double DISTENCE_TILL_1ST_CUBE_SCALE2 = 4*12 + 10 +5 + 14 + 3 + 4;
	
	// IF LEFT LOGIC:
	private static final double L_FORMAT_PATTERN = 0.0;
	private static final double L_INITAL_MOVE = 304.31 + 10.0;
	private static final double L_TO_R_INITIAL_MOVE = 231.0;
	private static final double L_TO_R_ACROSS_MOVE = 165; //Testing val is 193  was 264
	private static final double L_TO_R_NULL_ZONE = 33.0;
	private static final double L_TO_R_FINAL_MOVE = 80.0;
	public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

	// IF MID-LEFT LOGIC:
	private static final double ML_FORMAT_PATTERN = 0.0;
	private static final double ML_INITIAL_MOVE = 48.0;
	private static final double ML_ACROSS_MOVE = 55.5;

	private static final double ML_FINAL_MOVE = 266.31;
	private static final double ML_TO_R_INITIAL_MOVE = 48.0;
	private static final double ML_TO_R_ACROSS_MOVE = 192.0;
	private static final double ML_TO_R_FINAL_MOVE = 266.31;
	public static final String ML_DESCRIPTION = "Go to switch hot side from mid-left position and score";

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

	// IF MID-RIGHT LOGIC:
	private static final double MR_FORMAT_PATTERN = 0.0;
	private static final double MR_INITIAL_MOVE = 64.0;
	private static final double MR_ACROSS_MOVE = 140.0;
	private static final double MR_MIDDLE_DISTANCE = 56.77;
	private static final double MR_FINAL_MOVE = 167.01;
	private static final double MR_TO_L_INITIAL_MOVE = 64.0;
	private static final double MR_TO_L_ACROSS_MOVE = 182.9;
	private static final double MR_TO_L_NULL_ZONE = 140.0;
	private static final double MR_TO_L_ENDING_MOVE = 0.0;
	public static final String MR_DESCRIPTION = "Go to switch hot side from mid-right position and score";

	public ScaleAttackWithStageTwo(FieldSide whichSideOfTheScaleIsOurColor,
			StartPosition robotStartingPosition) {

		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			
			// Rays test 90 move
//			addSequential(new DriveStraightLockedGears(L_INITAL_MOVE - 30 - 12 - 5 - (4 *12), true));
//			addSequential(new Pivot(90));
//			addSequential(new DriveStraight(4 * 12));
//			addSequential(new Pivot(-90));
//			addSequential(new DriveStraight(2 * 12));
//			addSequential(new Pivot(180));
//			addSequential(new AutoPickUpCubeWithDistance(3 * 12));
//			addSequential(new DriveStraight(-12));
//			addSequential(new Pivot(180));
//			addSequential(new DriveStraight(3 * 12));
			
			
			// Normal turn 45
			addSequential(new DriveStraightLockedGears(L_INITAL_MOVE - 30 - 12, true));
			addSequential(new Pivot(45));
			addSequential(new DriveStraight(-4));
			//addSequential(new ScoreStartingCubeOnScale());
			addSequential(new Pivot(105 - 5));
			addSequential(new AutoPickUpCubeWithDistance(DISTENCE_TILL_1ST_CUBE_SCALE2));
			addSequential(new DriveStraight(-DISTENCE_TILL_1ST_CUBE_SCALE2));
			addSequential(new Pivot(-100));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			// Needs to be tested, we may want to turn 45 degrees instead on 90
			addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(L_TO_R_ACROSS_MOVE, true));
			addSequential(new Pivot(-90));
			//addSequential(new DriveStraightLockedGears(L_TO_R_NULL_ZONE, true));
			addSequential(new DriveStraightLockedGears(L_TO_R_FINAL_MOVE, true));
			addSequential(new ScoreStartingCubeOnScale());
			
			// Stage Two
			
//			addSequential(new DriveStraight(RE_CENTER_DISTANCE));
//			addSequential(new Pivot(-90.0));
//			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
//			addSequential(new Pivot(90));
//			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
//			addSequential(new Pivot(-90));
//			addSequential(new DriveStraight(DISTANCE_TO_GRAB_CUBE));
//			addSequential(new AutoPickUpCubeWithDrive());
//			addSequential(new DriveStraight(-DISTANCE_TO_GRAB_CUBE));
//			addSequential(new Pivot(-90));
//			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
//			addSequential(new Pivot(-90));
//			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
//			addSequential(new Pivot(-90));
//			addSequential(new ScoreStartingCubeOnScale());	
		}
		
		/*======================================*/
		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraightLockedGears(C_L_INITIAL_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraightLockedGears(C_L_MIDDLE_MOVE, true));
			addSequential(new Pivot(90));
			
			// This drivestraight needs to be changed, it theoretically would go to far
			addSequential(new DriveStraightLockedGears(C_L_FINAL_MOVE, true));
			addSequential(new Pivot(45));
			addSequential(new ScoreStartingCubeOnScale());
			
			// Stage Two
			
			addSequential(new DriveStraight(RE_CENTER_DISTANCE));
			addSequential(new Pivot(90.0));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TO_GRAB_CUBE));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_GRAB_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraightLockedGears(C_R_INITIAL_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(C_R_MIDDLE_MOVE, true));
			addSequential(new Pivot(-90));
			
			// This drivestraight needs to be changed, it theoretically would go to far
			addSequential(new DriveStraightLockedGears(C_R_FINAL_MOVE, true));
			addSequential(new Pivot(-45));
			addSequential(new ScoreStartingCubeOnScale());
			
			// Stage Two
			
			addSequential(new DriveStraight(RE_CENTER_DISTANCE));
			addSequential(new Pivot(-90.0));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TO_GRAB_CUBE));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_GRAB_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());	
		}
		
		/*======================================*/
		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraightLockedGears(L_TO_R_INITIAL_MOVE, true));
			addSequential(new Pivot(90));
			addSequential(new DriveStraightLockedGears(-L_TO_R_ACROSS_MOVE, true));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
			
			// Stage Two
			
			addSequential(new DriveStraight(RE_CENTER_DISTANCE));
			addSequential(new Pivot(90.0));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TO_GRAB_CUBE));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_GRAB_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraightLockedGears(R_INITAL_MOVE, true));
			addSequential(new Pivot(-45));
			addSequential(new ScoreStartingCubeOnScale());	
			
			// Stage Two
			
			addSequential(new DriveStraight(RE_CENTER_DISTANCE));
			addSequential(new Pivot(-90.0));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TO_GRAB_CUBE));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_GRAB_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());	
		}
		
		/*======================================*/
		// NO GAME DATA:
		else
		{
			addSequential(new AnyForward());
		}
	}

}
