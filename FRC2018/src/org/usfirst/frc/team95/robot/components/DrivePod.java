
package org.usfirst.frc.team95.robot.components;

import org.usfirst.frc.team95.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Each DrivePod represents one of the sides of the robot. Each pod consists of
 * 3 drive motors slaved into one gearbox, along with its shifter and shaft
 * encoder.
 */

public class DrivePod
	{
		// Leave these as the interface (CANSpeedController) rather than the concrete class
		// (CANTalon or AdjustedTalon) so that we can use the unit tests.
		// See "Liskov substitution principle".
		private CANSpeedController leader, follower1, follower2;
		private String name;

		// Provide the CAN addresses of the three motor controllers.
		// Set reverse to true if positive throttle values correspond to moving the
		// robot backwards.
		// (This is to account for the way the drive pods are mounted in a rotationally
		// symmetric way.)
		// Name is for feedback on the SmartDashboard - likely "left" or "right"
		public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum, boolean reverse)
			{
				this.name = name;


				// Connect each Talon
				CANTalon leader = new CANTalon(leaderCanNum);
				CANTalon follower1 = new CANTalon(follower1CanNum);
				CANTalon follower2 = new CANTalon(follower2CanNum);

				// Leaders have quadrature encoders connected to their inputs
				leader.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);

				// Tell the followers to follow the leader
				follower1.changeControlMode(CANTalon.TalonControlMode.Follower);
				follower1.set(leaderCanNum);
				follower2.changeControlMode(CANTalon.TalonControlMode.Follower);
				follower2.set(leaderCanNum);

				// TODO: How do we tell the CANTalon how many ticks per rev? Or do we?
				// Are all the speeds and distances expressed in ticks (/per second)?

				// TODO: How do we reverse a drive pod?
				
				this.leader = leader;
				this.follower1 = follower1;
				this.follower2 = follower2;

				// Add to LiveWindow
				LiveWindow.addActuator("Drive Train", name + " drive pod", (CANTalon) leader);
			}
		
		// Constructor used by tester.
		// This is a design pattern called "Dependency Injection".
		public DrivePod(String name, CANSpeedController leader, CANSpeedController follower1, CANSpeedController follower2) {
			this.name = name;
			this.leader    = leader;
			this.follower1 = follower1;
			this.follower2 = follower2;
		}
		
		// Provide a default value for reverse parameter
		public DrivePod(String name, int leaderCanNum, int follower1CanNum, int follower2CanNum)
			{
				this(name, leaderCanNum, follower1CanNum, follower2CanNum, false);
			}

		public void log()
			{
				// TODO: Anything we wanna see on the SmartDashboard, put here
				SmartDashboard.putNumber(name + " debug value", 1);
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
				// TODO: change mode as necessary
				leader.set(throttle);
				// followers follow
			}

		// Command a specific speed, to be enforced via PID control
		public void setSpeed(double speedInchesPerSecond)
			{
				// TODO
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
				// Specify which type of CANSpeedController by casting to CANTalon.
				// In the normal robot code, it is a safe assumption that leader, follower1, and follower1
				// are CANTalon objects (which can be safely stored in CANSpeedController variables because
				// the CANSpeedController class is a parent of the CANTalon class, and the robot code calls
				// the constructor that sets them to CANTalon objects.
				// Ask a coach if you want to know more!
				((CANTalon)leader   ).enableBrakeMode(isEnabled);
				((CANTalon)follower1).enableBrakeMode(isEnabled);
				((CANTalon)follower2).enableBrakeMode(isEnabled);
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
