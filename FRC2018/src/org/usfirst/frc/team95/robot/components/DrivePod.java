
package org.usfirst.frc.team95.robot.components;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Each DrivePod represents one of the sides of the robot. Each pod consists of
 * 3 drive motors slaved into one gearbox, along with its shifter and shaft
 * encoder.
 */

public class DrivePod {
	// Measured 2/13/18 on practice robot on "field" carpet
	private static final double ENCODER_TICKS_PER_INCH = 23840.0 / (4*12); //25560.0 / (4 * 12);
	private static final double ROBOT_MAX_SPEED_TICKS_PER_100MS = Constants.ROBOT_TOP_SPEED_LOW_GEAR_FPS * 12.0 * ENCODER_TICKS_PER_INCH / 10.0;
	private static final double K_F_POSITION_MODE = 0.0; // Not used in position mode
	private static final double K_P_POSITION_MODE = 0.4;// 0.6 * 1023.0 / (6*ENCODER_TICKS_PER_INCH); // Respond to an error of 6" with 60% throttle
	private static final double K_I_POSITION_MODE = 0.0; //0.01 * K_P;
	private static final double K_D_POSITION_MODE = 0.15; //40.0 * K_P;
	private static final double K_F_HIGH_POSITION_MODE = 0.0; // Not used in position mode
	private static final double K_P_HIGH_POSITION_MODE = 0.05;// 0.6 * 1023.0 / (6*ENCODER_TICKS_PER_INCH); // Respond to an error of 6" with 60% throttle
	private static final double K_I_HIGH_POSITION_MODE = 0.0; //0.01 * K_P;
	private static final double K_D_HIGH_POSITION_MODE = 0.35; //40.0 * K_P;
	private static final int I_ZONE_POSITION_MODE = 1000; // In closed loop error units
	
	// Feedforward term (K_F) is only used in closed-loop speed control.
	// The talon uses it to guess the appropriate throttle value for a given speed, before adjusting the throttle using
	// the P, I, and D terms.
	private static final double K_F_SPEED_MODE = 0.00; // 2018-3-21 It seems to behave in ways that don't make sense to me when this is nonzero 
	private static final double K_P_SPEED_MODE = 0.40; // 2018-3-21 determined by experimentation on doppler, with no load
	private static final double K_I_SPEED_MODE = 0.001; // 2018-3-21 determined by experimentation on doppler, with no load
	private static final double K_D_SPEED_MODE = 0.000; //40.0 * K_P;
	private static final double K_T_F_SPEED_MODE = 0.08; // 2018-3-21 It seems to behave in ways that don't make sense to me when this is nonzero 
	private static final double K_T_P_SPEED_MODE = 0.40; // 2018-3-21 determined by experimentation on doppler, with no load
	private static final double K_T_I_SPEED_MODE = 0.001; // 2018-3-21 determined by experimentation on doppler, with no load
	private static final double K_T_D_SPEED_MODE = 0.001; //40.0 * K_P;

	private static final int I_ZONE_SPEED_MODE = 1000; // In closed loop error units
	private String fLabel = "DrivePod F";
	private String pLabel = "DrivePod P";
	private String iLabel = "DrivePod I";
	private String dLabel = "DrivePod D";		
	private IMotorControllerEnhanced leader, follower1, follower2;
	private String name;
	private double twiddle = 1.0; // This value is used to force SmartDashboard line graphs to update by slightly changing the value
	private double targetDistanceAtSpeed; // When commanded both a distance and a speed, this object needs to remember the target distance
	private double targetDeltaSign; // When commanded both a distance and a speed, remember if the target is numerically greater or less than the present position.

	// Provide the CAN addresses of the three motor controllers.
	// Set reverse to true if positive throttle values correspond to moving the
	// robot backwards.
	// (This is to account for the way the drive pods are mounted in a rotationally
	// symmetric way.)
	// Name is for feedback on the SmartDashboard - likely "left" or "right"
	public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum, boolean reverse) {
		this.name = name;
		this.leader = new AdjustedTalon(leaderCanNum);
		this.follower1 = new AdjustedTalon(follower1CanNum);
		this.follower2 = new AdjustedTalon(follower2CanNum);

		// Tell the followers to follow the leader
		follower1.set(ControlMode.Follower, leaderCanNum);
		follower2.set(ControlMode.Follower, leaderCanNum);

		// TODO: figure out what to do with 'reverse' and do it here

		// Apply current limit settings to each AdjustedTalon
		applyCurrentLimitSettings(leader);
		applyCurrentLimitSettings(follower1);
		applyCurrentLimitSettings(follower2);

		init();
	}

	// Provide a default value for reverse parameter
	public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum) {
		this(name, leaderCanNum, follower1CanNum, follower2CanNum, false);
	}

	// Constructor used for unit tests
	public DrivePod(String name, IMotorControllerEnhanced leader, IMotorControllerEnhanced follower1,
			IMotorControllerEnhanced follower2, SolenoidI shifter) {
		this.name = name;
		this.leader = leader;
		this.follower1 = follower1;
		this.follower2 = follower2;

		init();
	}

	private void init() {
		// Leaders have quadrature encoders connected to their inputs
		leader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
		leader.setSensorPhase(true);

		leader.configForwardSoftLimitEnable(false, Constants.CAN_TIMEOUT_MS);
		leader.configReverseSoftLimitEnable(false, Constants.CAN_TIMEOUT_MS);

		// Send the initial PID constant values to the smartdash
		fLabel = name + " " + fLabel;
		pLabel = name + " " + pLabel;
		iLabel = name + " " + iLabel;
		dLabel = name + " " + dLabel;
		//SmartDashboard.putNumber(fLabel, K_F_SPEED_MODE);
		//SmartDashboard.putNumber(pLabel, K_P_SPEED_MODE);
		//SmartDashboard.putNumber(iLabel, K_I_SPEED_MODE);
		//SmartDashboard.putNumber(dLabel, K_D_SPEED_MODE);

	}
	// TODO: How do we tell the CANTalon how many ticks per rev? Or do we?
	// Are all the speeds and distances expressed in ticks (/per second)?

	/**
	 * Apply the PID+F constants that are used during closed-loop position mode
	 */
	private void applyPositionPidConsts() {
		leader.config_kF(Constants.PID_IDX, K_F_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kP(Constants.PID_IDX, K_P_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kI(Constants.PID_IDX, K_I_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kD(Constants.PID_IDX, K_D_POSITION_MODE, Constants.CAN_TIMEOUT_MS);	
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term
		// accumulator.
		leader.config_IntegralZone(Constants.PID_IDX, I_ZONE_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
	}
	
	private void applyHighGearPositionPidConsts() {
		leader.config_kF(Constants.PID_IDX, K_F_HIGH_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kP(Constants.PID_IDX, K_P_HIGH_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kI(Constants.PID_IDX, K_I_HIGH_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kD(Constants.PID_IDX, K_D_HIGH_POSITION_MODE, Constants.CAN_TIMEOUT_MS);	
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term
		// accumulator.
		leader.config_IntegralZone(Constants.PID_IDX, I_ZONE_POSITION_MODE, Constants.CAN_TIMEOUT_MS);
	}
	
	/**
	 * Apply the PID+F constants that are used during closed-loop speed mode
	 */
	public void applySpeedPidConsts() {
		leader.config_kF(Constants.PID_IDX, K_F_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kP(Constants.PID_IDX, K_P_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kI(Constants.PID_IDX, K_I_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kD(Constants.PID_IDX, K_D_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term
		// accumulator.
		leader.config_IntegralZone(Constants.PID_IDX, I_ZONE_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
	}
	
	private void applyTurningSpeedPidConsts() {
		leader.config_kF(Constants.PID_IDX, K_T_F_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kP(Constants.PID_IDX, K_T_P_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kI(Constants.PID_IDX, K_T_I_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		leader.config_kD(Constants.PID_IDX, K_T_D_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the I term
		// accumulator.
		leader.config_IntegralZone(Constants.PID_IDX, I_ZONE_SPEED_MODE, Constants.CAN_TIMEOUT_MS);
	}
	
	/**
	 * Command the DrivePod to a position relative to current position
	 * 
	 * @param inches
	 *            - the target position in inches from current position
	 */
	public void setCLPosition(double inches) {
		if(Robot.drivebase.getGear())
		{
			applyHighGearPositionPidConsts();
			System.out.println("IN HIGH GEAR");
		}else {
			applyPositionPidConsts();
			System.out.println("IN LOW GEAR");
		}
		
		double delta = ENCODER_TICKS_PER_INCH * inches;
		double current = leader.getSelectedSensorPosition(Constants.PID_IDX);
		System.out.println(name + " going " + inches + " inches, or delta=" + delta + ", current = " + current);
		leader.set(ControlMode.Position, current + delta);
	}

//	public void reUpdatePIDValues() {
//		
//		if(getControlMode() == ControlMode.Position) {
//			if(Robot.drivebase.getGear())
//			{
//				applyHighGearPositionPidConsts();
//			}else {
//				applyPositionPidConsts();
//			}
//		}
//		else if(getControlMode() == ControlMode.Velocity) {
//			applySpeedPidConsts();
//		}
//		else {
//			
//		}
//	}
	
	/**
	 * Command the DrivePod to take on a specific velocity until commanded to stop
	 * 
	 * @param inchesPerSecond
	 *            - the target velocity in inches per second
	 */
	public void setCLSpeed(double inchesPerSecond, boolean turning) {
		if(turning) {
			applyTurningSpeedPidConsts();
			double speedTicksPer100ms = inchesPerSecond * ENCODER_TICKS_PER_INCH / 10.0;
			leader.set(ControlMode.Velocity, speedTicksPer100ms);
		}
		else {
			applySpeedPidConsts();
			double speedTicksPer100ms = inchesPerSecond * ENCODER_TICKS_PER_INCH / 10.0;
			leader.set(ControlMode.Velocity, speedTicksPer100ms);	
		}	
	}

	/**
	 * Drive for a given distance at a given speed.
	 * This method will use closed-loop control on the speed,
	 * but only a simple threshold on the distance - it'll drive until the threshold is passed.
	 * 
	 * @param inchesPerSecond - the target velocity in inches per second
	 * @param inches - the target distance in inches
	 */
	public void driveForDistanceAtSpeed(double inchesPerSecond, double inches) {
		targetDistanceAtSpeed = getPositionInches() - inches;
		if(inches < 0) {
			targetDeltaSign = 1.0;
		} else {
			targetDeltaSign = -1.0;
		}
		System.out.println(name + " seeking a rate of " + inchesPerSecond + " inches per second for " + inches + ", sign=" + targetDeltaSign + ".");
		setCLSpeed(inchesPerSecond, false);
	}
	
	public double getPositionInches() {
		return leader.getSelectedSensorPosition(Constants.PID_IDX) / ENCODER_TICKS_PER_INCH;
	}

	public double getTargetPositionInches() {
		if (getControlMode() == ControlMode.Position) {
			return ((TalonSrxWrapper) leader).getClosedLoopTarget(Constants.PID_IDX) / ENCODER_TICKS_PER_INCH;
		} else if (getControlMode() == ControlMode.Velocity) {
			return targetDistanceAtSpeed;
		} else {
			return 0;
		}
	}
	
	public double getTargetVelocityInchesPerSecond() {
		if (getControlMode() == ControlMode.Velocity) {
			double speedTicksPer100ms = ((TalonSrxWrapper) leader).getClosedLoopTarget(Constants.PID_IDX);
			return (speedTicksPer100ms / ENCODER_TICKS_PER_INCH) * 10.0;
		} else {
			return 0;
		}
	}
	
	public ControlMode getControlMode() {
		if(leader instanceof TalonSrxWrapper) {
			return ((TalonSrxWrapper) leader).getControlMode();
		} else {
			return ControlMode.PercentOutput; // Return something somewhat reasonable
		}
	}
	
	public void log() {
		if (twiddle > 1.0) {
			twiddle = 1.0;
		} else {
			twiddle = 1.000001;
		}
		// Anything we wanna see on the SmartDashboard, put here. Use "name",
		// which should be "left" or "right".
		SmartDashboard.putNumber(name + " position (in)", twiddle * getPositionInches());
		SmartDashboard.putNumber(name + " target pos (in)", twiddle * getTargetPositionInches());
		SmartDashboard.putNumber(name + " velocity (inps)", twiddle * getEncoderVelocityFeetPerSecond()*12.0);
		SmartDashboard.putNumber(name + " target velocity (inps)", twiddle * getTargetVelocityInchesPerSecond());
		SmartDashboard.putNumber("BUSvoltage", leader.getBusVoltage());
		SmartDashboard.putNumber("OutputVoltage", leader.getMotorOutputVoltage());
		SmartDashboard.putNumber("eIZone", leader.configGetParameter(314, Constants.CAN_ORDINAL_SLOT0, Constants.CAN_TIMEOUT_MS));
		SmartDashboard.putNumber("eIValue", leader.configGetParameter(311, Constants.CAN_ORDINAL_SLOT0, Constants.CAN_TIMEOUT_MS));
	}

	// Throttle here is the traditional value, between -1.0 and 1.0, indicating
	// how  much power should
	// be applied to the motor. It corresponds well to speed.
	public void setThrottle(double throttle) {
		// This is the only set...() method where we don't need to call either 
		// applySpeedPidConsts() or applyPositionPidConsts().
		leader.set(ControlMode.PercentOutput, throttle);
		// followers follow
	}

	// Max speed back and forward, always make this number positve when setting it.
	public void setMaxSpeed(double maxSpeed) {
		leader.configPeakOutputForward(maxSpeed, Constants.CAN_TIMEOUT_MS);
		leader.configPeakOutputReverse(-maxSpeed, Constants.CAN_TIMEOUT_MS);
	}
	
	public void setVoltageRamp(double rampRate) {
		leader.configOpenloopRamp(rampRate, Constants.CAN_TIMEOUT_MS);
	}

	public void enableBrakeMode(boolean isEnabled) {
		leader.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
		follower1.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
		follower2.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
	}

	/**
	 * 
	 * @return true when the drivepod is close enough to its target
	 */
	public boolean isOnTarget() {
		// leader.configNeutralDeadband(percentDeadband, timeoutMs);
		if(getControlMode() == ControlMode.Position) {
			return Math.abs(getPositionInches() - getTargetPositionInches()) < Constants.DRIVEPOD_ON_TARGET_THRESHOLD_INCHES;
		} else if (getControlMode() == ControlMode.Velocity) {
			return (getPositionInches() * targetDeltaSign) > (targetDistanceAtSpeed * targetDeltaSign);
		} else {
			return true; // When you're not seeking anything, you're already at your destination.
		}
	}

	/**
	 * Retrieve the values of P, I and D from the smartdashboard and apply them
	 * to the motor controllers.
	 */
	public void pullPidConstantsFromSmartDash() {
		// Retrieve
		//K_F_SPEED_MODE = SmartDashboard.getNumber(fLabel, K_F_SPEED_MODE);
		//K_P_SPEED_MODE = SmartDashboard.getNumber(pLabel, K_P_SPEED_MODE);
		//K_I_SPEED_MODE = SmartDashboard.getNumber(iLabel, K_I_SPEED_MODE);
		//K_D_SPEED_MODE = SmartDashboard.getNumber(dLabel, K_D_SPEED_MODE);

		// Apply
		applySpeedPidConsts();
	}

	public double getQuadEncPos() {
		return leader.getSelectedSensorPosition(Constants.PID_IDX);
	}

	public double getEncoderVelocityFeetPerSecond() {
		return (leader.getSelectedSensorVelocity(Constants.PID_IDX)) * (1/(ENCODER_TICKS_PER_INCH * 12)) * (10/1);
	}

	public double getLeadCurrent() {
		return leader.getOutputCurrent();
	}

	public static void applyCurrentLimitSettings(IMotorControllerEnhanced mc) {
		mc.configContinuousCurrentLimit(Constants.DRIVEPOD_MAX_CURRENT_CONTINUAL_AMPS, Constants.CAN_TIMEOUT_MS);
		mc.configPeakCurrentLimit(Constants.DRIVEPOD_MAX_CURRENT_PEAK_AMPS, Constants.CAN_TIMEOUT_MS);
		mc.configPeakCurrentDuration(Constants.DRIVEPOD_MAX_CURRENT_PEAK_DURATION_MS, Constants.CAN_TIMEOUT_MS);
	}

	public void voltageCurrentComp() {
		// Notes of GitHub
		// leader.setControlMode(TalonControlMode.Voltage);
		// leader.setVoltageCompensationRampRate(rampRate);
		// leader.set(rate);
	}
}
