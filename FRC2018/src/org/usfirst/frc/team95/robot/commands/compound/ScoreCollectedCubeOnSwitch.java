package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreCollectedCubeOnSwitch extends CommandGroup {

	// This command assumes a cube has already been collected.
	public ScoreCollectedCubeOnSwitch() {
		addSequential(new SetElevatorHeight(ElevatorHoldPoint.SWITCH_SCORE));
	}
}
