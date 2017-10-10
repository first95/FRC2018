package org.usfirst.frc.team95.robot.components;

import com.ctre.CANTalon;

/**
 * Each DrivePod represents one of the sides of the robot.  Each pod consists of 3 drive motors slaved into one gearbox,
 * along with its shifter and shaft encoder.
 */

public class DrivePod {
	private CANTalon leader, follower1, follower2;
	
	
	// Provide the CAN addresses of the three motor controllers.
	// Set reverse to true if positive throttle values correspond to moving the robot backwards.
	public DrivePod(int leaderCanNum, int follower1CanNum,  int follower2CanNum, boolean reverse) {
		// Connect each Talon
		leader    = new CANTalon(leaderCanNum);
		follower1 = new CANTalon(follower1CanNum);
		follower2 = new CANTalon(follower2CanNum);
		
		// Tell the followers to follow the leader
		follower1.changeControlMode(CANTalon.TalonControlMode.Follower);
		follower1.set(leaderCanNum);
		follower2.changeControlMode(CANTalon.TalonControlMode.Follower);
		follower2.set(leaderCanNum);
		
		// TODO: Does it work to set followers to be reversed?  We should check this with a follower that
		// is not mechanically slaved to the leader, so that they won't fight each other if going in
		// opposite directions.  Otherwise we should just set a flag, and multiply all speeds by -1.
	}
	
	// Provide a default value for reverse parameter
	public DrivePod(int leaderCanNum, int follower1CanNum,  int follower2CanNum) {
		this(leaderCanNum, follower1CanNum, follower2CanNum, false);
	}
	
	// Throttle here is the traditional value, between -1.0 and 1.0, indicating how much power should
	// be applied to the motor.  It corresponds well to speed.
	public void setThrottle(double throttle) {
		// TODO: change mode as necessary
		leader.set(throttle);
	}
	
	// Command a specific speed, to be enforced via PID control
	public void setSpeed(double speedInchesPerSecond) {
		// TODO
	}
	
	// Command that this side of the robot should travel a specific distance along the carpet.
	// Note that unless the other pod is commanded to travel the same distance, this will not
	// sweep out a straight line.
	public void travelDistance(double inchesToTravel) {
		// TODO
	}
	
	// Returns true if and only if the drive pod has achieved the distance commanded by
	// the most recent call to travelDistance()
	public boolean hasFinishedTravelingDistanceCommanded() {
		// TODO
		return true;
	}
}
