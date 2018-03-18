package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.drivebase.DriveStraight;
import org.usfirst.frc.team95.robot.commands.drivebase.Pivot;
import org.usfirst.frc.team95.robot.commands.drivebase.SweepTurn;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchAttackStageTwo extends CommandGroup {

	// GENERAL LOGIC:
	private static final double DISTANCE_TILL_CENTER = ((42.0 - 18.0) + 23.0);
	private static final double DISTANCE_TILL_SECOND_CUBE = (48.0 + 12.0);
	private static final double DISTANCE_TO_RE_CENTER_WITH_CUBE = 10.0;

	public SwitchAttackStageTwo(FieldSide whichSideOfTheScaleIsOurColor, StartPosition robotStartingPosition) {
		if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraight(-DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_RE_CENTER_WITH_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else if (robotStartingPosition == StartPosition.LEFT && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraight(-DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_RE_CENTER_WITH_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else if (robotStartingPosition == StartPosition.CENTER && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraight(-DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_RE_CENTER_WITH_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else if (robotStartingPosition == StartPosition.CENTER && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraight(-DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_RE_CENTER_WITH_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.LEFT) {
			addSequential(new DriveStraight(-DISTANCE_TILL_CENTER));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_RE_CENTER_WITH_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new ScoreStartingCubeOnSwitch());
		} else if (robotStartingPosition == StartPosition.RIGHT && whichSideOfTheScaleIsOurColor == FieldSide.RIGHT) {
			addSequential(new DriveStraight(-DISTANCE_TILL_CENTER));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(90));
			addSequential(new AutoPickUpCubeWithDrive());
			addSequential(new DriveStraight(-DISTANCE_TO_RE_CENTER_WITH_CUBE));
			addSequential(new Pivot(90));
			addSequential(new DriveStraight(DISTANCE_TILL_SECOND_CUBE));
			addSequential(new Pivot(-90));
			addSequential(new DriveStraight(DISTANCE_TILL_CENTER));
			addSequential(new ScoreStartingCubeOnSwitch());
		}
	}
}
