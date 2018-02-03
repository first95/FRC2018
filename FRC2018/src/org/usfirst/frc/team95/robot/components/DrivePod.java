
package org.usfirst.frc.team95.robot.components;

import org.usfirst.frc.team95.robot.Constants;

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

public class DrivePod
	{
		private static final double K_P = 0.4 * 1023.0 / 900.0; // Respond to an error of 900 with 40% throttle
		private static final double K_I = 0.01 * K_P;
		private static final double K_D = 0; //40.0 * K_P;
		private static final int I_ZONE = 200; // In closed loop error units
		private IMotorControllerEnhanced leader, follower1, follower2;
		private static final double FEET_PER_ENCODER_TICK = 1.0; // TODO
		private String name;
		private FeedbackDevice encoder;

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
				
				// Configure the right talon for closed loop control
				leader.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
				leader.setSensorPhase(true);
				leader.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
				leader.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
				leader.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
				// Prevent Integral Windup.
				// Whenever the control loop error is outside this zone, zero out the I term accumulator.
				leader.config_IntegralZone(Constants.PID_IDX, I_ZONE, Constants.CAN_TIMEOUT_MS);
				
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

				encoder = FeedbackDevice.QuadEncoder;
				
				// Leaders have quadrature encoders connected to their inputs
				leader.configSelectedFeedbackSensor(encoder, Constants.PID_IDX, Constants.CAN_TIMEOUT_MS);
				leader.setSensorPhase(false);
				
				
				// Not being used at the moment
				// voltageCurrentLimit();
				// voltageCurrentComp();

				// TODO: How do we tell the CANTalon how many ticks per rev? Or do we?
				// Are all the speeds and distances expressed in ticks (/per second)?

				// TODO: How do we reverse a drive pod?

			}
		
		/**
		 * Command the DrivePod to a specific height
		 * @param feet - the target height in feet up from lowest possible position
		 */
		public void setDrivePodPosition(double feet) {
			double encoderTicks = feet / FEET_PER_ENCODER_TICK;
			leader.set(ControlMode.Position, encoderTicks);
		}
		
		public double getDrivePodPosition() {
			return leader.getSelectedSensorPosition(Constants.PID_IDX) * FEET_PER_ENCODER_TICK;
		}
		

		public void log()
			{
				// TODO: Anything we wanna see on the SmartDashboard, put here
				SmartDashboard.putNumber(name + " debug value", 1);
				SmartDashboard.putNumber("BUSvoltage", leader.getBusVoltage());
				SmartDashboard.putNumber("OutputVoltage", leader.getMotorOutputVoltage());
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

		// Command that this side of the robot should travel a specific distance along
		// the carpet.
		// Note that unless the other pod is commanded to travel the same distance, this
		// will not
		// sweep out a straight line.
		// Call this once to command distance - do not call repeatedly, as this will
		// reset the
		// distance remaining.
		public void travelDistance(double inchesToTravel, double speedInchesPerSecond)
			{
				// TODO
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

		public void voltageCurrentLimit()
			{
				// Notes of GitHub
				// leader.setCurrentLimit(amps);
				// leader.EnableCurrentLimit(boolean);
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
	}
