package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.commands.TestArmSet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TestArm extends Subsystem {
	private TalonSRX motor = new TalonSRX(1);
	public TestArm() {
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TestArmSet());
	}
	
	// Experiment with different set modes here
	public void setMotor(double value) {
		motor.set(ControlMode.Position, value);
	}

}
