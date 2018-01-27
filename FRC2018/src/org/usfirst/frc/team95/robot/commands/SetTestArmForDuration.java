package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class SetTestArmForDuration extends TimedCommand {
	double value;
	
	public SetTestArmForDuration(double seconds, double value) {
		super(seconds);
		this.value = value;
		requires(Robot.testArm);
	}
//	
//	@Override
//	public synchronized void start() {
//		
//	}

	@Override
	protected void execute() {

		// This method is called once every loop through the robot code.
		double throttle = Constants.weaponStick.getRawAxis(Constants.TEST_ARM_AXIS);
		Robot.testArm.setMotor(throttle);
	}
	
	@Override
	protected void end() {
		Robot.testArm.stopMotor();
	}

}
