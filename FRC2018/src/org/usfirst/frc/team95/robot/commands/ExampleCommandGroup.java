package org.usfirst.frc.team95.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExampleCommandGroup extends CommandGroup {

	DriveStraight DR;
	ShiftGear SG;
	
	public ExampleCommandGroup() {
		
		DR = new DriveStraight(4000);
		SG = new ShiftGear();
		isFinished();
		
	}
	
	
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return super.isFinished();
	}
}
