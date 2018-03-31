package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.OI;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.climber.ManuallyControlClimber;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
//import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
//import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {

	// private IMotorControllerEnhanced climberDriver;
	private IMotorControllerEnhanced leftClimbDriver, rightClimbDriver;
	public static final double FEET_FULL_RANGE = 71.0 / 12.0; // Copied from elevator
	public static final double ENCODER_TICKS_FULL_RANGE = 78400.0; // Copied from elevator
	private static final double TICKS_PER_FOOT = ENCODER_TICKS_FULL_RANGE / FEET_FULL_RANGE;

	private static final double K_F = 0.0; // Don't use in position mode.
	private double K_P = 0.4 * 1023.0 / 900.0; // Respond to an error of 900 with 40% throttle
	private double K_I = 0.01 * K_P;
	private double K_D = 0; // 40.0 * K_P;
	private static final int I_ZONE = 200; // In closed loop error units
	private final String pLabel = "Climb P";
	private final String iLabel = "Climb I";
	private final String dLabel = "Climb D";
	private static final double SOFT_FWD_LIMIT = ENCODER_TICKS_FULL_RANGE * 0.96;

	public Climber() {
		super();
		// Set up the digital IO object to read the home switch
		
		leftClimbDriver = new AdjustedTalon(Constants.LEFT_CLIMBER_DRIVER);
		rightClimbDriver = new AdjustedTalon(Constants.RIGHT_CLIMBER_DRIVER);

		// Configure the left talon to follow the right talon, but backwards
		leftClimbDriver.setInverted(true); // Inverted here refers to the output
		leftClimbDriver.set(ControlMode.Follower, Constants.RIGHT_CLIMBER_DRIVER);		

		// Configure the right talon for closed loop control
		rightClimbDriver.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_IDX,
				Constants.CAN_TIMEOUT_MS);
		rightClimbDriver.setSensorPhase(true);
		rightClimbDriver.config_kF(Constants.PID_IDX, K_F, Constants.CAN_TIMEOUT_MS);
		rightClimbDriver.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
		rightClimbDriver.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
		rightClimbDriver.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term
		// accumulator.
		rightClimbDriver.config_IntegralZone(Constants.PID_IDX, I_ZONE, Constants.CAN_TIMEOUT_MS);
		//
		// //Configure soft limit at top
		// climberDriver.configForwardSoftLimitEnable(true, Constants.CAN_TIMEOUT_MS);
		// climberDriver.configForwardSoftLimitThreshold((int) SOFT_FWD_LIMIT,
		// Constants.CAN_TIMEOUT_MS);
		// climberDriver.configReverseSoftLimitEnable(false,
		// Constants.CAN_TIMEOUT_MS);
		//
		// //Tell talon a limit switch is connected
		// rightElevDriver.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector,
		// LimitSwitchNormal.NormallyOpen, Constants.CAN_TIMEOUT_MS);
		//
		 //Send the initial PID constant values to the smartdash
		 SmartDashboard.putNumber(pLabel, K_P);
		 SmartDashboard.putNumber(iLabel, K_I);
		 SmartDashboard.putNumber(dLabel, K_D);
	}

	// public void setCurrentPosToZero() {
	// climberDriver.setSelectedSensorPosition(0, Constants.PID_IDX,
	// Constants.CAN_TIMEOUT_MS);
	// }

	public void brake(boolean isEnabled) {
		 rightClimbDriver.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlClimber());
	}

	public void log() {

	}

	public void setClimberSpeed(double value) {

		System.out.println("CLIMBER RUNNING!");
		
		if (Robot.oi.getClimberPOV() == OI.D_POV_UP) {
			rightClimbDriver.set(ControlMode.PercentOutput, value);
			System.out.println("CLIMBER UP");
		} else if (Robot.oi.getClimberPOV() == OI.D_POV_DOWN) {
			rightClimbDriver.set(ControlMode.PercentOutput, -value);
			System.out.println("CLIMBER DOWN");
		} else {
			rightClimbDriver.set(ControlMode.PercentOutput, 0);
		}

		// if(getClimberEncoderTicks() <= 10 || value > 0) {
		// // Either the elevator is above the deck, or being driven upward.
		// // This is the normal state
		// climberDriver.set(ControlMode.PercentOutput, value);
		// } else {
		// // The elevator is on the deck and they're trying to drive down.
		// // Don't do that.
		// climberDriver.set(ControlMode.PercentOutput, 0);
		// }

	}

	// public void setClimberHeight(double feet) {
	// climberDriver.set(ControlMode.Position, feet * TICKS_PER_FOOT);
	// }

	// public double getClimberHeightFeet() {
	// return climberDriver.getSelectedSensorPosition(Constants.PID_IDX) /
	// TICKS_PER_FOOT;
	// }

//	public double getClimberEncoderTicks() {
//		return climberDriver.getSelectedSensorPosition(Constants.PID_IDX);
//	}

	// public double getTargetHeightFeet() {
	// if (climberDriver instanceof AdjustedTalon) {
	// return ((AdjustedTalon) climberDriver).getClosedLoopTarget(Constants.PID_IDX)
	// / TICKS_PER_FOOT;
	// } else {
	// return 0;
	// }
	// }

	public void stopMotor() {
		rightClimbDriver.set(ControlMode.PercentOutput, 0.0);
	}

	 public void pullPidConstantsFromSmartDash() {
	 // Retrieve
	 K_P = SmartDashboard.getNumber(pLabel, K_P);
	 K_I = SmartDashboard.getNumber(iLabel, K_I);
	 K_D = SmartDashboard.getNumber(dLabel, K_D);
	
	 // Apply
	 rightClimbDriver.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
	 rightClimbDriver.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
	 rightClimbDriver.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
	 }

	// public boolean isOnTarget() {
	// // leader.configNeutralDeadband(percentDeadband, timeoutMs);
	// return Math.abs(getClimberHeightFeet() - getTargetHeightFeet()) <
	// (Constants.ELEVATOR_ON_TARGET_THRESHOLD_INCHES
	// / 12.0);
	// }
}
