package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.ManuallyControlElevator;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	private static final double K_F = 0.0; // Don't use in position mode.
	private static final double K_P = 0.4 * 1023.0 / 900.0; // Respond to an error of 900 with 40% throttle
	private static final double K_I = 0.01 * K_P;
	private static final double K_D = 40.0 * K_P;
	private static final int I_ZONE = 200; // In closed loop error units
	
	private static final double FEET_PER_ENCODER_TICK = 1.0; // TODO
	
	private IMotorControllerEnhanced leftElevDriver, rightElevDriver;
	
	public Elevator() {
		super();
		leftElevDriver  = new AdjustedTalon(Constants.LEFT_ELEV_DRIVER);
		rightElevDriver = new AdjustedTalon(Constants.RIGHT_ELEV_DRIVER);

		// Configure the left talon to follow the right talon, but backwards
		leftElevDriver.setInverted(true); // Inverted here refers to the output 
		leftElevDriver.set(ControlMode.Follower, Constants.RIGHT_ELEV_DRIVER);
		
		// Configure the right talon for closed loop control
		rightElevDriver.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kF(Constants.PID_IDX, K_F, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term accumulator.
		rightElevDriver.config_IntegralZone(Constants.PID_IDX, I_ZONE, Constants.CAN_TIMEOUT_MS);
		// Consider the winch's current position to be the elevator bottom
		setCurrentPosToZero();
	}

	/**
	 * Update the Talon's definition of zero to be its present position.
	 * 
	 * SAFETY NOTE: The talon tracks position changes as long as it has power, no matter
	 * how many times you restart the code.  You can easily turn a motor a zillion
	 * times, and the talon is counting each and every one of those revolutions.
	 * 
	 * If you then command the motor to see position 0, it will make all haste
	 * to turn it back as many revolutions as you've turned the shaft since power-on.
	 * 
	 * This method needs to be called at the beginning of a match when the elevator
	 * is known to be at its bottom, and it should be called whenever the sensor
	 * confirms that it's hit bottom.
	 * 
	 */
	private void setCurrentPosToZero() {
		rightElevDriver.setSelectedSensorPosition(0, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlElevator()); 
	}
	
	public void log() {
		SmartDashboard.putNumber("Elevator Speed", Robot.oi.getElevatorSpeed());
//		SmartDashboard.putNumber("leftElevEncoder Value:", leftElevEncoder.PulseWidthEncodedPosition.value);
		SmartDashboard.putNumber("rightElevEncoder Value:", rightElevDriver.getSelectedSensorPosition(Constants.PID_IDX));
		SmartDashboard.putNumber("Height in feet:", getElevatorHeightFeet());
	}
//	
//	/**
//	 * Command the elevator to run at a specific speed
//	 * @param value - the throttle value to apply to the motors
//	 */
//	public void setElevatorSpeed(double value) {
//		rightElevDriver.set(ControlMode.PercentOutput, value);
//	}
//	
	/**
	 * Command the elevator to a specific height
	 * @param feet - the target height in feet up from lowest possible position
	 */
	public void setElevatorHeight(double feet) {
		double encoderTicks = feet / FEET_PER_ENCODER_TICK;
		rightElevDriver.set(ControlMode.Position, encoderTicks);
	}
	
	public double getElevatorHeightFeet() {
		return rightElevDriver.getSelectedSensorPosition(Constants.PID_IDX) * FEET_PER_ENCODER_TICK;
	}
	
	/**
	 * Commands the motor to stop driving itself, but not to disable.
	 * Turns off closed-loop control completely.
	 */
	public void stopMotor() {
		rightElevDriver.set(ControlMode.PercentOutput, 0.0);
	}
}
