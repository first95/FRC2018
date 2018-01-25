
package org.usfirst.frc.team95.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Each DrivePod represents one of the sides of the robot. Each pod consists of
 * 3 drive motors slaved into one gearbox, along with its shifter and shaft
 * encoder.
 */

public class DrivePod
	{
		private IMotorControllerEnhanced leader, follower1, follower2;

		private SolenoidI shifter;
		private String name;
		private FeedbackDevice encoder;

		// Provide the CAN addresses of the three motor controllers.
		// Set reverse to true if positive throttle values correspond to moving the
		// robot backwards.
		// (This is to account for the way the drive pods are mounted in a rotationally
		// symmetric way.)
		// Name is for feedback on the SmartDashboard - likely "left" or "right"
		public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum, int shifterNumber, boolean reverse)
			{
				this.name = name;
				this.leader = new AdjustedTalon(leaderCanNum);
				this.follower1 = new AdjustedTalon(follower1CanNum);
				this.follower2 = new AdjustedTalon(follower2CanNum);
				this.shifter = new SolenoidWrapper(shifterNumber);

				// Tell the followers to follow the leader
				follower1.set(ControlMode.Follower, leaderCanNum);
				follower2.set(ControlMode.Follower, leaderCanNum);
				

				init();
			}

		// Provide a default value for reverse parameter
		public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum, int shifterNumber)
			{
				this(name, leaderCanNum, follower1CanNum, follower2CanNum, shifterNumber, false);
			}

		// Constructor used for unit tests
		public DrivePod(String name, IMotorControllerEnhanced leader, IMotorControllerEnhanced follower1, IMotorControllerEnhanced follower2, SolenoidI shifter)
			{
				this.name = name;
				this.leader = leader;
				this.follower1 = follower1;
				this.follower2 = follower2;
				this.shifter = shifter;

				init();
			}

		private void init()
			{

				encoder = FeedbackDevice.QuadEncoder;
				// Leaders have quadrature encoders connected to their inputs
				leader.configSelectedFeedbackSensor(encoder, 0, 0);
				
				
				
				voltageCurrentLimit();
				voltageCurrentComp();

				// TODO: How do we tell the CANTalon how many ticks per rev? Or do we?
				// Are all the speeds and distances expressed in ticks (/per second)?

				// TODO: How do we reverse a drive pod?

			}

		public void log()
			{
				// TODO: Anything we wanna see on the SmartDashboard, put here
				SmartDashboard.putNumber(name + " debug value", 1);
				SmartDashboard.putNumber("BUSvoltage", leader.getBusVoltage());
				SmartDashboard.putNumber("OutputVoltage", leader.getMotorOutputVoltage());
				//SmartDashboard.putNumber("Encoder", leader.getSensorCollection());
				
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

				// Temporary gearshift algorithm - replace with a better one
				if (Math.abs(leader.getOutputCurrent()) > 1.5)
					{
						setGear(false);
					}
				else
					{
						setGear(true);
					}
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

		public void setGear(boolean isHighGear)
			{
				shifter.set(isHighGear);
			}

		public void enableBrakeMode(boolean isEnabled)
			{
				leader.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
				follower1.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
				follower2.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
			}

		public void getQuadEncoders()
			{			
				
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
