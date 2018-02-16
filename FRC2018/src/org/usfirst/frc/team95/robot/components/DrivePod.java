
package org.usfirst.frc.team95.robot.components;

import org.usfirst.frc.team95.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Each DrivePod represents one of the sides of the robot. Each pod consists of
 * 3 drive motors slaved into one gearbox, along with its shifter and shaft
 * encoder.
 */

public class DrivePod
	{
		private static final double ENCODER_TICKS_PER_INCH = 25560.0 / (4*12); // Measured 2/13/18 on practice robot on "field" carpet
		// 31396.0 / (4*12); // Measured 2018-2-9 on the practice robot
		private double K_P = 0.6 * 1023.0 / (6*ENCODER_TICKS_PER_INCH); // Respond to an error of 6" with 60% throttle
		private double K_I = 0.25 * K_P;
		private double K_D = 15.0 * K_P;
		private static final int I_ZONE = 20; // In closed loop error units
		private String pLabel = "DrivePod P";
		private String iLabel = "DrivePod I";
		private String dLabel = "DrivePod D";		
		private IMotorControllerEnhanced leader, follower1, follower2;
		private String name;
		private double twiddle = 0;


		// Provide the CAN addresses of the three motor controllers.
		// Set reverse to true if positive throttle values correspond to moving the
		// robot backwards.
		// (This is to account for the way the drive pods are mounted in a rotationally
		// symmetric way.)
		// Name is for feedback on the SmartDashboard - likely "left" or "right"
		public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum, boolean reverse)
			{
				this.name = name;
				this.leader = new AdjustedTalon(leaderCanNum);
				this.follower1 = new AdjustedTalon(follower1CanNum);
				this.follower2 = new AdjustedTalon(follower2CanNum);

				// Tell the followers to follow the leader
				follower1.set(ControlMode.Follower, leaderCanNum);
				follower2.set(ControlMode.Follower, leaderCanNum);			
				
				// TODO: figure out what to do with 'reverse' and do it here						
				
				// Apply current limit settings	to each AdjustedTalon
				applyCurrentLimitSettings(leader);
				applyCurrentLimitSettings(follower1);
				applyCurrentLimitSettings(follower2);

				init();
			}

		// Provide a default value for reverse parameter
		public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum)
			{
				this(name, leaderCanNum, follower1CanNum, follower2CanNum, false);
			}

		// Constructor used for unit tests
		public DrivePod(String name, IMotorControllerEnhanced leader, IMotorControllerEnhanced follower1, IMotorControllerEnhanced follower2, SolenoidI shifter)
			{
				this.name = name;
				this.leader = leader;
				this.follower1 = follower1;
				this.follower2 = follower2;

				init();
			}

		private void init()
			{
				// Leaders have quadrature encoders connected to their inputs
				leader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
				leader.setSensorPhase(true);
				
				leader.configForwardSoftLimitEnable(false, Constants.CAN_TIMEOUT_MS);
				leader.configReverseSoftLimitEnable(false, Constants.CAN_TIMEOUT_MS);				
			
				leader.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
				leader.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
				leader.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
				// Prevent Integral Windup.
				// Whenever the control loop error is outside this zone, zero out the I term accumulator.
				leader.config_IntegralZone(Constants.PID_IDX, I_ZONE, Constants.CAN_TIMEOUT_MS);				
			
				// Send the initial PID constant values to the smartdash
				pLabel = name + " " + pLabel;
				iLabel = name + " " + iLabel;
				dLabel = name + " " + dLabel;
				SmartDashboard.putNumber(pLabel, K_P);
				SmartDashboard.putNumber(iLabel, K_I);
				SmartDashboard.putNumber(dLabel, K_D);		
				
				// Zero out the encoder to start out.
				// This isn't strictly necessary but it makes for a nice odometer.
				leader.setSelectedSensorPosition(0, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
				
				
				// Not being used at the moment
				// voltageCurrentLimit();
				// voltageCurrentComp();

				// TODO: How do we tell the CANTalon how many ticks per rev? Or do we?
				// Are all the speeds and distances expressed in ticks (/per second)?

				// TODO: How do we reverse a drive pod?

			}

		
		/**
		 * Command the DrivePod to a position relative to current position
		 * @param inches - the target position in inches from current position
		 */
		public void setCLPosition(double inches) {
			double delta = ENCODER_TICKS_PER_INCH * inches;
			double current = leader.getSelectedSensorPosition(Constants.PID_IDX);
			System.out.println(name + " delta="+delta + ", current = " + current);
			leader.set(ControlMode.Position, current+delta);
		}
		
		public double getPositionInches() {
			return leader.getSelectedSensorPosition(Constants.PID_IDX) / ENCODER_TICKS_PER_INCH;
		}
		
		public double getTargetPositionInches() {
			if(leader instanceof AdjustedTalon) {
				return ((AdjustedTalon)leader).getClosedLoopTarget(Constants.PID_IDX) / ENCODER_TICKS_PER_INCH;
			} else {
				return 0;
			}
		}

		public void log()
			{
				if (twiddle > 0) { twiddle = 0; } else { twiddle = 0.00000000000001; }
				// Anything we wanna see on the SmartDashboard, put here.  Use "name", which should be "left" or "right".
				SmartDashboard.putNumber(name + " position", twiddle + getPositionInches());
				SmartDashboard.putNumber(name + " target", twiddle + getTargetPositionInches());
			}

		public void reset()
			{
				// TODO: anything that needs to be reset on an initialization should go here.
				// Namely, zero out any record of distance traveled.
			}

		// Throttle here is the traditional value, between -1.0 and 1.0, indicating how
		// much power should
		// be applied to the motor. It corresponds well to speed.
		public void setThrottle(double throttle)
			{
				leader.set(ControlMode.PercentOutput, throttle);
				// followers follow
			}

		// Command a specific speed, to be enforced via PID control
		public void setSpeed(double speedInchesPerSecond)
			{
				// TODO: this won't work without some settings getting applied first
				// leader.set(ControlMode.Velocity, speedInchesPerSecond);
				// followers follow
			}

		public void enableBrakeMode(boolean isEnabled)
			{
				leader.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
				follower1.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
				follower2.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
			}

		public double getQuadEncPos()
			{			
				return leader.getSelectedSensorPosition(Constants.PID_IDX);
			}

		public double getLeadCurrent()
			{
				return leader.getOutputCurrent();
			}
		
		public static void applyCurrentLimitSettings(IMotorControllerEnhanced mc)
			{
				mc.configContinuousCurrentLimit(Constants.DRIVEPOD_MAX_CURRENT_CONTINUAL_AMPS  , Constants.CAN_TIMEOUT_MS);
				mc.configPeakCurrentLimit      (Constants.DRIVEPOD_MAX_CURRENT_PEAK_AMPS       , Constants.CAN_TIMEOUT_MS);
				mc.configPeakCurrentDuration   (Constants.DRIVEPOD_MAX_CURRENT_PEAK_DURATION_MS, Constants.CAN_TIMEOUT_MS);				
			}

		public void voltageCurrentComp()
			{
				// Notes of GitHub
				// leader.setControlMode(TalonControlMode.Voltage);
				// leader.setVoltageCompensationRampRate(rampRate);
				// leader.set(rate);
			}

		// Returns true if and only if the drive pod has achieved the distance commanded
		// by
		// the most recent call to travelDistance()
		public boolean isOnTarget()
			{
				// TODO
				return true;
			}
		

		/**
		 * Retrieve the values of P, I and D from the smartdashboard and apply them
		 * to the motor controllers.
		 */
		public void pullPidConstantsFromSmartDash() {
			// Retrieve
			K_P = SmartDashboard.getNumber(pLabel, K_P);
			K_I = SmartDashboard.getNumber(iLabel, K_I);
			K_D = SmartDashboard.getNumber(dLabel, K_D);
			
			// Apply
			leader.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
			leader.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
			leader.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
		}
	}
