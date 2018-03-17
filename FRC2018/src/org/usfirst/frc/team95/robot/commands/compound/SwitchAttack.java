package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.PivotAtSpeed;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchAttack extends CommandGroup
{
	// These strategies assumes we have a cube pre-loaded on the robot.
	private static final double AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES = 0; //2; // During auto moves to score on the switch, move up this close to the switch wall.
	private static final double ONE_FOOT = 12.0;
	
	
	// IF LEFT LOGIC:
	private static final double L_FORMAT_PATTERN = 0.0;
	private static final double L_INITIAL_MOVE = 148.19;
	private static final double L_TO_R_MOVE = 158.5;
	private static final double L_TO_SWITCH_MOVE = 156;
	private static final double L_FINAL_MOVE = 19.56;
	public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

	// IF MID-LEFT LOGIC:
	private static final double ML_FORMAT_PATTERN = 0.0;
	private static final double ML_INITIAL_MOVE = 0.0;
	private static final double ML_ENDING_MOVE = 0.0;
	private static final double ML_TO_SWITCH_MOVE = 156;
	private static final double ML_TO_R_MOVE = 153.5;
	private static final double ML_SWEEPER_TURN_RADIUS = 71.61;
	private static final double ML_SWEEPER_DEGREES = 32.73;
	public static final String ML_DESCRIPTION = "Go to switch hot side from mid-left position and score";

	// IF CENTER LOGIC:
	private static final double C_FORMAT_PATTERN = 0.0;
	private static final double C_INITAL_MOVE = 48.0 - 18.0;
	private static final double C_R_DISTANCE_IN_THE_MIDDLE = 48.0;
	private static final double C__R_FINAL_MOVE = 48.0+18.0 + 18.0;
	private static final double C_L_DISTANCE_IN_THE_MIDDLE = 48.0+15.0;
	private static final double C__L_FINAL_MOVE = 48.0 + 18.0 + 18.0;
	public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";

	// IF RIGHT LOGIC:
	private static final double R_FORMAT_PATTERN = 0.0;
	private static final double R_INITAL_MOVE = 148.19;
	private static final double R_FINAL_MOVE = 19.56;
	private static final double R_TO_L_INITIAL_MOVE = 12.0;
	private static final double R_TO_L_MOVE = 158.5;
	private static final double R_TO_SWITCH_MOVE = 156;
	public static final String R_DESCRIPTION = "Go to switch hot side from right position and score";

	// IF MID-RIGHT LOGIC:
	private static final double MR_FORMAT_PATTERN = 0.0;
	private static final double MR_TO_SWITCH_MOVE = 156;
	private static final double MR_TO_L_MOVE = 153.5;
	private static final double MR_DISTANCE_STRIGHT = 101.6; // Measured in Solidworks, 2018-2-15 //100.82;
	//private static final double MR_DISTANCE_STRIGHT = 24.0;
	public static final String MR_DESCRIPTION = "Go to switch hot side from mid-right position and score";

	public SwitchAttack(FieldSide whichSideOfTheNearSwitchIsOurColor,
							StartPosition robotStartingPosition)
	{
		
		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(L_INITIAL_MOVE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(L_FINAL_MOVE-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
		else if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
		{
			// This sequence crosses the open space in front of the switch.  We haven't tested it yet but it looks good.
//			addSequential(new DriveStraight(L_ONE_FOOT));
//			addSequential(new Pivot(90));
//			addSequential(new DriveStraight(L_TO_R_MOVE));
//			addSequential(new Pivot(-90));
//			addSequential(new DriveStraight(L_TO_SWITCH_MOVE));
//			addSequential(new ScoreStartingCubeOnSwitch());
			
			// This sequence throws the cube over the center of the switch, scoring on the opposite side.  We're not sure we have the range for it.
			addSequential(new DriveStraight(L_INITIAL_MOVE));
			addSequential(new Pivot(90));
			// Intentionally try to go a little further than the actual distance
			addSequential(new DriveStraight(L_FINAL_MOVE+AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
		
		/*======================================*/
		// MID-LEFT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.MID_LEFT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
		{
			// Just for now, do it the way the mid right does
			addSequential(new DriveStraight(MR_DISTANCE_STRIGHT-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());				
		}
		else if (robotStartingPosition == StartPosition.MID_LEFT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraight(ONE_FOOT));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(ML_TO_R_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(ML_TO_SWITCH_MOVE));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
		
		/*======================================*/
		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {
			
			addSequential(new DriveStraight(C_INITAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(C_L_DISTANCE_IN_THE_MIDDLE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(C__L_FINAL_MOVE));
			addSequential(new ScoreStartingCubeOnSwitch());	

		} else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(C_INITAL_MOVE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(C_R_DISTANCE_IN_THE_MIDDLE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(C__R_FINAL_MOVE));
			addSequential(new ScoreStartingCubeOnSwitch());
			
		}

		/*======================================*/
		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(R_TO_L_INITIAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(R_TO_L_MOVE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(R_TO_SWITCH_MOVE));
			addSequential(new ScoreStartingCubeOnSwitch());
			
			// This sequence throws the cube over the center of the switch, scoring on the opposite side.  We're not sure we have the range for it.
			//addSequential(new DriveStraight(R_INITAL_MOVE));
			//addSequential(new Pivot(-90));
			// Intentionally try to go a little further than the actual distance
			//addSequential(new DriveStraight(R_FINAL_MOVE+AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			//addSequential(new ScoreStartingCubeOnSwitch());
		}
		else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraight(R_INITAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(R_FINAL_MOVE-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
		}

		/*======================================*/
		// MID-RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.MID_RIGHT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(ONE_FOOT));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(MR_TO_L_MOVE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(MR_TO_SWITCH_MOVE));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
		else if (robotStartingPosition == StartPosition.MID_RIGHT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(MR_DISTANCE_STRIGHT-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
			
		}
		
		/*======================================*/
		// NO GAME DATA:
		else {
			System.out.println("NO GAME DATA FOUND");
			addSequential(new AnyForward());
		}
	}
}
