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
	private static final double AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES = 0; //2; // During auto moves to score on the switch, move up this close to the switch wall.
	
	// IF LEFT LOGIC:
	private static final double L_FORMAT_PATTERN = 0.0;
	private static final double L_INITIAL_MOVE = 148.19;
	private static final double L_TO_R_MOVE = 158.5;
	private static final double L_TO_SWITCH_MOVE = 156;
	private static final double L_ONE_FOOT = 12.0;
	private static final double L_FINAL_MOVE = 19.56;
	public static final String L_DESCRIPTION = "Go to switch hot side from left position and score";

	// IF MID-LEFT LOGIC:
	private static final double ML_FORMAT_PATTERN = 0.0;
	private static final double ML_INITAL_MOVE = 12.0;
	private static final double ML_SWEEPER_TURN_RADIUS = 71.61;
	private static final double ML_SWEEPER_DEGREES = 32.73;
	private static final double ML_ENDING_MOVE = 12.0;
	public static final String ML_DESCRIPTION = "Go to switch hot side from mid-left position and score";

	// IF CENTER LOGIC:
	private static final double C_FORMAT_PATTERN = 0.0;
	private static final double C_INITAL_MOVE = 12.0;
	private static final double C_ENDING_MOVE = 12.0;
	private static final double C_R_SWEEPER_RADIUS = 36.0;
	private static final double C_R_SWEEPER_ANGLE = 47.22;
	private static final double C_R_DISTANCE_IN_THE_MIDDLE = 35.66;
	private static final double C_L_SWEEPER_RADIUS = 36.0;
	private static final double C_L_SWEEPER_ANGLE = 57.29;
	private static final double C_L_DISTANCE_IN_THE_MIDDLE = 30.5;
	public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";

	// IF RIGHT LOGIC:
	private static final double R_FORMAT_PATTERN = 0.0;
	private static final double R_INITAL_MOVE = 148.19;
	private static final double R_FINAL_MOVE = 19.56;
	public static final String R_DESCRIPTION = "Go to switch hot side from right position and score";

	// IF MID-RIGHT LOGIC:
	private static final double MR_FORMAT_PATTERN = 0.0;
	private static final double MR_DISTANCE_STRIGHT = 101.6; // Measured in Solidworks, 2018-2-15 //100.82;
	//private static final double MR_DISTANCE_STRIGHT = 24.0;
	public static final String MR_DESCRIPTION = "Go to switch hot side from mid-right position and score";

	public SwitchAttack(FieldSide whichSideOfTheNearSwitchIsOurColor, StartPosition robotStartingPosition) {
		
		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraight(L_INITIAL_MOVE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(L_FINAL_MOVE-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
		else if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT)
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
			addSequential(new ScoreStartingCubeOverSwitch());
		}
		
		/*======================================*/
		// MID-LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.MID_LEFT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {
			
			if(false) { 
				// TODO: Fix sweeper moves and the enable this again
				addSequential(new DriveStraight(ML_INITAL_MOVE));
				addSequential(new SweepTurn(ML_SWEEPER_DEGREES, ML_SWEEPER_TURN_RADIUS));
				addSequential(new SweepTurn(-ML_SWEEPER_DEGREES, ML_SWEEPER_TURN_RADIUS));
				addSequential(new DriveStraight(ML_ENDING_MOVE - AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
				addSequential(new ScoreStartingCubeOnSwitch());
			} else {
				// Just for now, do it the way the mid right does
				addSequential(new DriveStraight(MR_DISTANCE_STRIGHT-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
				addSequential(new ScoreStartingCubeOnSwitch());				
			}

		} else if (robotStartingPosition == StartPosition.MID_LEFT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {
			
			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");

		}
		
		/*======================================*/
		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {
			
			addSequential(new DriveStraight(C_INITAL_MOVE));
			addSequential(new SweepTurn(-C_L_SWEEPER_ANGLE, C_L_SWEEPER_RADIUS));
			addSequential(new DriveStraight(C_L_DISTANCE_IN_THE_MIDDLE));
			addSequential(new SweepTurn(C_L_SWEEPER_ANGLE, C_L_SWEEPER_RADIUS));
			addSequential(new DriveStraight(C_ENDING_MOVE-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());

		} else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(C_INITAL_MOVE));
			addSequential(new SweepTurn(C_R_SWEEPER_ANGLE, C_R_SWEEPER_RADIUS));
			addSequential(new DriveStraight(C_R_DISTANCE_IN_THE_MIDDLE));
			addSequential(new SweepTurn(-C_R_SWEEPER_ANGLE, C_R_SWEEPER_RADIUS));
			addSequential(new DriveStraight(C_ENDING_MOVE-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
			
		}

		/*======================================*/
		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {

			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");
			
		} else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

			addSequential(new DriveStraight(R_INITAL_MOVE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(R_FINAL_MOVE-AUTO_MOVE_SWITCH_SCORE_STANDOFF_INCHES));
			addSequential(new ScoreStartingCubeOnSwitch());
			
		}

		/*======================================*/
		// MID-RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.MID_RIGHT && whichSideOfTheNearSwitchIsOurColor == FieldSide.LEFT) {

			// NO PATH MAPPED YET
			System.out.println("NO PATH MAPPED!!!");
			
		} else if (robotStartingPosition == StartPosition.MID_RIGHT
				&& whichSideOfTheNearSwitchIsOurColor == FieldSide.RIGHT) {

			System.out.println("RAN MID_RIGHT AND RIGHT COLOR");
			System.out.println("RAN MID_RIGHT AND RIGHT COLOR");
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
