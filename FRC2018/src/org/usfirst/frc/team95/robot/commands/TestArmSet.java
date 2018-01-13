package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TestArmSet extends Command {
	public TestArmSet() {
		requires(Robot.testArm);
	}

	@Override
	protected void execute() {
		// This method is called once every loop through the robot code.
		double throttle = Constants.weaponStick.getRawAxis(Constants.TEST_ARM_AXIS);
		Robot.testArm.setMotorThrottle(throttle);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
