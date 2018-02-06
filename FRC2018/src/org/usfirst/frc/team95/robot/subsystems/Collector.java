package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.ManuallyControlCollector;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends Subsystem {
	private IMotorControllerEnhanced leftChainDriver, rightChainDriver;
	
	// The solenoids for the piston that opens the maw, and operates the wrist
	private SolenoidI mawOpener, wristStageOne, wristStageTwo;
	
	public Collector() {
		super();
		leftChainDriver  = new AdjustedTalon(Constants.LEFT_CHAIN_DRIVER);
		rightChainDriver = new AdjustedTalon(Constants.RIGHT_CHAIN_DRIVER);
		mawOpener = new SolenoidWrapper(Constants.COLLECTOR_SOLENOID_NUM);
		
		// False means it is extended
		wristStageOne = new SolenoidWrapper(Constants.WRIST_STAGE_ONE);
		wristStageTwo = new SolenoidWrapper(Constants.WRIST_STAGE_TWO);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlCollector());
	}
	
	public void log() {
		SmartDashboard.putBoolean("Maw Open?" , Robot.oi.getCollectorOpen());
		SmartDashboard.putBoolean("Wrist Stage One Extended?", Robot.oi.getWristStageOneRetracted());
		SmartDashboard.putBoolean("Wrist Stage Two Extended?", Robot.oi.getWristStageTwoRetracted());
	}

	public void setMawOpen(boolean open) {
		mawOpener.set(open);
	}
	
	public void setWristStageOneRetracted(boolean retracted) {
		wristStageOne.set(retracted);
	}
	
	public void setWristStageTwoRetracted(boolean retracted) {
		wristStageTwo.set(retracted);
	}
	
	
	public void setIntakeSpeed(double value) {
		leftChainDriver.set(ControlMode.PercentOutput, -value);
		rightChainDriver.set(ControlMode.PercentOutput, value);
	}
}
