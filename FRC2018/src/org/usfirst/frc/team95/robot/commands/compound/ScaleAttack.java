package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.AnyForward;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleAttack extends CommandGroup {

	// These strategies assumes we have a cube pre-loaded on the robot.
	private static final double INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION = 12;
	private static final double R_INCHES_TO_TRAVEL_TO_SCALE = 303.71;
	private static final double L_INCHES_TO_TRAVEL_TO_SCALE = 304.31;
	// IF LEFT LOGIC:
	
	//IF MID-LEFT LOGIC:
	private static final double ML_DEGREES_FOR_SWEEP_TURN = 28.19;
	private static final double ML_INCHES_FOR_SWEEP_TURN_RADIUS = 162.29;
	// IF CENTER LOGIC:
	// This is the distance to travel between the two sweeping turns
	private static final double C_R_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 69.10;
	private static final double C_L_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 80.56;
	private static final double C_R_INCHES_TO_TRAVEL_TO_SCALE = 167.01;
	private static final double C_L_INCHES_TO_TRAVEL_TO_SCALE = 139.10;
	private static final double C_DEGREES_FOR_SWEEP_TURN = 54.34;
	private static final double C_INCHES_FOR_SWEEP_TURN_RADIUS = 48.0;
	private static final double C_INCHES_FOR_SWEEP_TURN_RADIUS_SPECIAL = 82.74;
	public static final String C_DESCRIPTION = "Go to switch hot side from center position and score";
	
	//IF MID-RIGHT LOGIC:
	private static final double MR_INCHES_FOR_SWEEP_TURN_RADIUS = 36.0;
	private static final double MR_DEGREES_FOR_SWEEP_TURN = 45.0;
	private static final double MR_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS = 56.77;
	private static final double MR_INCHES_TO_TRAVEL_TO_SCALE = 200.65;
	
	//IF RIGHT LOGIC: (VALUES NEED TO BE CALCULATED!!!)

	public ScaleAttack(FieldSide whichSideOfTheScaleIsOurColor,
			StartPosition robotStartingPosition) {
		// LEFT SIDE MOVE:
		if (robotStartingPosition == StartPosition.LEFT
			&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(L_INCHES_TO_TRAVEL_TO_SCALE));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if (robotStartingPosition == StartPosition.LEFT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			
		}
		//MID-LEFT MOVE:
		else if (robotStartingPosition == StartPosition.MID_LEFT
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(-ML_DEGREES_FOR_SWEEP_TURN, ML_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new SweepTurn(ML_DEGREES_FOR_SWEEP_TURN, ML_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(L_INCHES_TO_TRAVEL_TO_SCALE));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
		}

		// CENTER MOVE:
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT)
		{
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(-C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_L_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS_SPECIAL));
			addSequential(new DriveStraight(C_L_INCHES_TO_TRAVEL_TO_SCALE));
			addSequential(new Pivot(90));
			addSequential(new ScoreStartingCubeOnScale());
		}
		else if (robotStartingPosition == StartPosition.CENTER
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_R_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(-C_DEGREES_FOR_SWEEP_TURN, C_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(C_R_INCHES_TO_TRAVEL_TO_SCALE));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
		}
		
		//MID-RIGHT MOVE:
		else if (robotStartingPosition == StartPosition.MID_RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			
		}
		else if (robotStartingPosition == StartPosition.MID_RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraight(INCHES_TO_TRAVEL_BEFORE_OR_AFTER_ROTATION));
			addSequential(new SweepTurn(MR_DEGREES_FOR_SWEEP_TURN, MR_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(MR_INCHES_TO_TRAVEL_BETWEEN_ROTATIONS));
			addSequential(new SweepTurn(-MR_DEGREES_FOR_SWEEP_TURN, MR_INCHES_FOR_SWEEP_TURN_RADIUS));
			addSequential(new DriveStraight(MR_INCHES_TO_TRAVEL_TO_SCALE));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
		}

		// RIGHT SIDE MOVE:
		else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {

		} 
		else if (robotStartingPosition == StartPosition.RIGHT
				&& whichSideOfTheScaleIsOurColor == FieldSide.RIGHT)
		{
			addSequential(new DriveStraight(R_INCHES_TO_TRAVEL_TO_SCALE));
			addSequential(new Pivot(-90));
			addSequential(new ScoreStartingCubeOnScale());
		}

		// NO GAME DATA:
		else {
			addSequential(new AnyForward());
		}
	}

}
