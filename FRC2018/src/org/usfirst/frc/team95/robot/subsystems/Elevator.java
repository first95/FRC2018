package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.elevator.ManuallyControlElevator;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	private static final double K_F = 0.0; // Don't use in position mode.
	private double K_P = 0.4 * 1023.0 / 900.0; // Respond to an error of 900 with 40% throttle
	private double K_I = 0.01 * K_P;
	private double K_D = 0; // 40.0 * K_P;
	private static final int I_ZONE = 200; // In closed loop error units
	private final String pLabel = "Winch P";
	private final String iLabel = "Winch I";
	private final String dLabel = "Winch D";
	public static final double FEET_FULL_RANGE = 71.0 / 12.0; // How many feet the elevator can move. Measured 2018-2-3
																// on practice robot
	public static final double ENCODER_TICKS_FULL_RANGE = 78400.0; // How many encoder ticks the elevator can move.
																	// Measured 2018-2-3 on practice robot
	private static final double TICKS_PER_FOOT = ENCODER_TICKS_FULL_RANGE / FEET_FULL_RANGE;
	private static final double SOFT_FWD_LIMIT = ENCODER_TICKS_FULL_RANGE * 0.96;

	private IMotorControllerEnhanced leftElevDriver, rightElevDriver;
	private DigitalInput homeSwitch;

	public Elevator() {
		super();
		// Set up the digital IO object to read the home switch
		homeSwitch = new DigitalInput(Constants.ELEVATOR_HOME_SWITCH_DIO_NUM);

		leftElevDriver = new AdjustedTalon(Constants.LEFT_ELEV_DRIVER);
		rightElevDriver = new AdjustedTalon(Constants.RIGHT_ELEV_DRIVER);

		// Configure the left talon to follow the right talon, but backwards
		leftElevDriver.setInverted(true); // Inverted here refers to the output
		leftElevDriver.set(ControlMode.Follower, Constants.RIGHT_ELEV_DRIVER);
		
		// Configure the right talon for closed loop control
		rightElevDriver.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_IDX,
				Constants.CAN_TIMEOUT_MS);
		rightElevDriver.setSensorPhase(true);
		rightElevDriver.config_kF(Constants.PID_IDX, K_F, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term
		// accumulator.
		rightElevDriver.config_IntegralZone(Constants.PID_IDX, I_ZONE, Constants.CAN_TIMEOUT_MS);

		// Configure soft limit at top
		//rightElevDriver.configForwardSoftLimitEnable(true, Constants.CAN_TIMEOUT_MS);
		//rightElevDriver.configForwardSoftLimitThreshold((int) SOFT_FWD_LIMIT, Constants.CAN_TIMEOUT_MS);
		//rightElevDriver.configReverseSoftLimitEnable(false, Constants.CAN_TIMEOUT_MS);
		
		//Tell talon a limit switch is connected
		rightElevDriver.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.CAN_TIMEOUT_MS);

		// Send the initial PID constant values to the smartdash
		// SmartDashboard.putNumber(pLabel, K_P);
		// SmartDashboard.putNumber(iLabel, K_I);
		// SmartDashboard.putNumber(dLabel, K_D);
	}

	public void checkAndApplyHomingSwitch() {
		// Pin floats high by default, due to an internal pull-up resistor.
		// When the magnet gets close enough to the reed switch, the pin is
		// connected to ground. Thus, get() starts returning false.
		if (elevatorIsHome()) {
			setCurrentPosToZero();
		}
	}
	
	private boolean elevatorIsHome() {
		return !homeSwitch.get();
	}

	/**
	 * Update the Talon's definition of zero to be its present position.
	 * 
	 * SAFETY NOTE: The talon tracks position changes as long as it has power, no
	 * matter how many times you restart the code. You can easily turn a motor a
	 * zillion times, and the talon is counting each and every one of those
	 * revolutions.
	 * 
	 * If you then command the motor to see position 0, it will make all haste to
	 * turn it back as many revolutions as you've turned the shaft since power-on.
	 * 
	 * This method needs to be called at the beginning of a match when the elevator
	 * is known to be at its bottom, and it should be called whenever the sensor
	 * confirms that it's hit bottom.
	 * 
	 */
	public void setCurrentPosToZero() {
		rightElevDriver.setSelectedSensorPosition(0, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
	}
	
	public void brake(boolean isEnabled) {
		rightElevDriver.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
		leftElevDriver.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
		}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlElevator());
	}

	public void log() {
		SmartDashboard.putNumber("Elevator Speed", Robot.oi.getElevatorSpeed());
		SmartDashboard.putBoolean("Elevator Home Switch", homeSwitch.get());
		SmartDashboard.putNumber("leftElevEncoder Value:", leftElevDriver.getSelectedSensorPosition(Constants.PID_IDX));
		SmartDashboard.putNumber("rightElevEncoder Value:",
				rightElevDriver.getSelectedSensorPosition(Constants.PID_IDX));
		SmartDashboard.putNumber("Elevator height in feet:", getElevatorHeightFeet());
	}

	/**
	 * Command the elevator to run at a specific speed.
	 * Won't drive downward if the homing switch is tripped.
	 * 
	 * @param value
	 *            - the throttle value to apply to the motors, between -1 and +1
	 */
	public void setElevatorSpeed(double value) {
		if(!elevatorIsHome() || value > 0) {
			// Either the elevator is above the deck, or being driven upward.
			// This is the normal state
			rightElevDriver.set(ControlMode.PercentOutput, value);
		} else {
			// The elevator is on the deck and they're trying to drive down.
			// Don't do that.
			rightElevDriver.set(ControlMode.PercentOutput, 0);
		}
	}

	/**
	 * Command the elevator to a specific height
	 * 
	 * @param feet
	 *            - the target height in feet up from lowest possible position
	 */
	public void setElevatorHeight(double feet) {
		rightElevDriver.set(ControlMode.Position, feet * TICKS_PER_FOOT);
	}

	/**
	 * Gets the elevator's present actual height, in feet. This may not be the same
	 * as the last height commanded to the elevator.
	 * 
	 * @return 0 for against the floor, about 5.91 for its highest extent.
	 */
	public double getElevatorHeightFeet() {
		return rightElevDriver.getSelectedSensorPosition(Constants.PID_IDX) / TICKS_PER_FOOT;
	}

	/**
	 * Gets the elevator's target height, in feet. This may not be the same as the
	 * actual height of the elevator.
	 * 
	 * @return 0 for against the floor, about 5.91 for its highest extent.
	 */
	public double getTargetHeightFeet() {
		if (rightElevDriver instanceof AdjustedTalon) {
			return ((AdjustedTalon) rightElevDriver).getClosedLoopTarget(Constants.PID_IDX) / TICKS_PER_FOOT;
		} else {
			return 0;
		}
	}

	/**
	 * Commands the motor to stop driving itself, but not to disable. Turns off
	 * closed-loop control completely.
	 */
	public void stopMotor() {
		rightElevDriver.set(ControlMode.PercentOutput, 0.0);
	}

	/**
	 * Retrieve the values of P, I and D from the smartdashboard and apply them to
	 * the motor controllers.
	 */
	public void pullPidConstantsFromSmartDash() {
		// Retrieve
		K_P = SmartDashboard.getNumber(pLabel, K_P);
		K_I = SmartDashboard.getNumber(iLabel, K_I);
		K_D = SmartDashboard.getNumber(dLabel, K_D);

		// Apply
		rightElevDriver.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
		rightElevDriver.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
	}

	public boolean isOnTarget() {
		// leader.configNeutralDeadband(percentDeadband, timeoutMs);
		return Math.abs(getElevatorHeightFeet()
				- getTargetHeightFeet()) < (Constants.ELEVATOR_ON_TARGET_THRESHOLD_INCHES / 12.0);
	}
}
