package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.commands.TestArmSet;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TestArm extends Subsystem {
	private TalonSRX motor = new TalonSRX(1);
	
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TestArmSet());
	}

}
