package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.ManuallyControlElevator;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	private IMotorControllerEnhanced leftElevDriver, rightElevDriver;
	private FeedbackDevice leftElevEncoder, rightElevEncoder;
	
	public Elevator() {
		super();
		leftElevDriver  = new AdjustedTalon(Constants.LEFT_ELEV_DRIVER);
		rightElevDriver = new AdjustedTalon(Constants.RIGHT_ELEV_DRIVER);
		leftElevEncoder = FeedbackDevice.CTRE_MagEncoder_Absolute;
		rightElevEncoder = FeedbackDevice.CTRE_MagEncoder_Absolute;

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlElevator());
	}
	
	public void log() {
		SmartDashboard.putNumber("Elevator Speed", Robot.oi.getElevatorSpeed());
		SmartDashboard.putNumber("leftElevEncoder Value:", leftElevEncoder.PulseWidthEncodedPosition.value);
		SmartDashboard.putNumber("rightElevEncoder Value:", rightElevEncoder.PulseWidthEncodedPosition.value);
	}
	
	public void setElevatorSpeed(double value) {
		leftElevDriver.set(ControlMode.PercentOutput, -value);
		rightElevDriver.set(ControlMode.PercentOutput, value);
	}
}
