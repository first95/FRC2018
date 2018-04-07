package org.usfirst.frc.team95.robot.commands.compound;

import org.usfirst.frc.team95.robot.commands.Pause;
import org.usfirst.frc.team95.robot.commands.collector.EjectCube;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle;
import org.usfirst.frc.team95.robot.commands.collector.SetWristAngle.WristAngle;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight;
import org.usfirst.frc.team95.robot.commands.elevator.SetElevatorHeight.ElevatorHoldPoint;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ElevateCubeAndScore extends CommandGroup {

	public ElevateCubeAndScore(ElevatorHoldPoint position, boolean isSwitch) {
		addSequential(new SetElevatorHeight(position));
		addSequential(new SetWristAngle(WristAngle.MID_DOWN));
		addSequential(new Pause(1.0));
		if(isSwitch) {
		addSequential(new ReleaseCube());
		}else {
			addSequential(new EjectCube());
		}
	}

}
