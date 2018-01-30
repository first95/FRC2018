package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.commands.ManuallyControlCollector;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Collector extends Subsystem {
	private IMotorControllerEnhanced leftChainDriver, rightChainDriver;
	
	// The solenoid for the piston that opens the maw
	private SolenoidI mawOpener;
	
	public Collector() {
		super();
		leftChainDriver  = new AdjustedTalon(Constants.LEFT_CHAIN_DRIVER);
		rightChainDriver = new AdjustedTalon(Constants.RIGHT_CHAIN_DRIVER);
		mawOpener = new SolenoidWrapper(Constants.COLLECTOR_SOLENOID_NUM);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlCollector());

	}

	public void setMawOpen(boolean open) {
		mawOpener.set(open);
	}
	
	public void setIntakeSpeed(double value) {
		leftChainDriver.set(ControlMode.PercentOutput, -value);
		rightChainDriver.set(ControlMode.PercentOutput, value);
	}
}
