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

		leftElevDriver.setInverted(true);
		leftElevDriver.set(ControlMode.Follower, Constants.RIGHT_ELEV_DRIVER);
		rightElevDriver.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
		
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
	}
	
	/**
	 * Command the elevator to run at a specific speed
	 * @param value - the throttle value to apply to the motors
	 */
	public void setElevatorSpeed(double value) {
		rightElevDriver.set(ControlMode.PercentOutput, value);
	}
	
	/**
	 * Command the elevator to a specific height
	 * @param feet - the target height in feet up from lowest possible position
	 */
	public void setElevatorPosition(double feet) {
		
	}
}
