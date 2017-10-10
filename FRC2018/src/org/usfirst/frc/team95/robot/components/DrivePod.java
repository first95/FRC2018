package org.usfirst.frc.team95.robot.components;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Each DrivePod represents one of the sides of the robot.  Each pod consists of 3 drive motors slaved into one gearbox,
 * along with its shifter and shaft encoder.
 */

public class DrivePod {
	private CANTalon leader, follower1, follower2;
	private final double DEFAULT_SPEED_INCHES_PER_S = 20;
	private String name;
	
	// Provide the CAN addresses of the three motor controllers.
	// Set reverse to true if positive throttle values correspond to moving the robot backwards.
	// (This is to account for the way the drive pods are mounted in a rotationally symmetric way.)
	// Name is for feedback on the SmartDashboard - likely "left" or "right"
	public DrivePod(String name, int leaderCanNum, int follower1CanNum,  int follower2CanNum, boolean reverse) {
		this.name = name;
		
		// Connect each Talon
		leader    = new CANTalon(leaderCanNum);
		follower1 = new CANTalon(follower1CanNum);
		follower2 = new CANTalon(follower2CanNum);
		
		// Leaders have quadrature encoders connected to their inputs
		leader.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		
		// Tell the followers to follow the leader
		follower1.changeControlMode(CANTalon.TalonControlMode.Follower);
		follower1.set(leaderCanNum);
		follower2.changeControlMode(CANTalon.TalonControlMode.Follower);
		follower2.set(leaderCanNum);
		
		// TODO: How do we tell the CANTalon how many ticks per rev?  Or do we?
		// Are all the speeds and distances expressed in ticks (/per second)?
		
		// TODO: How do we reverse a drive pod?
		
		// Add to LiveWindow
		LiveWindow.addActuator("Drive Train", name + " drive pod", (CANTalon) leader);
	}
	
	// Provide a default value for reverse parameter
	public DrivePod(String name, int leaderCanNum, int follower1CanNum,  int follower2CanNum) {
		this(name, leaderCanNum, follower1CanNum, follower2CanNum, false);
	}
	
	public void log() {
		// TODO: Anything we wanna see on the SmartDashboard, put here
		SmartDashboard.putNumber(name + " debug value", 1);
	}
	
	public void reset() {
		// TODO: anything that needs to be reset on an initialization should go here.
		// Namely, zero out any record of distance traveled.
	}
	
	// Throttle here is the traditional value, between -1.0 and 1.0, indicating how much power should
	// be applied to the motor.  It corresponds well to speed.
	public void setThrottle(double throttle) {
		// TODO: change mode as necessary
		leader.set(throttle);
		// followers follow
	}
	
	// Command a specific speed, to be enforced via PID control
	public void setSpeed(double speedInchesPerSecond) {
		// TODO
	}
	
	// Command that this side of the robot should travel a specific distance along the carpet.
	// Note that unless the other pod is commanded to travel the same distance, this will not
	// sweep out a straight line.
	// Call this once to command distance - do not call repeatedly, as this will reset the
	// distance remaining.
	public void travelDistance(double inchesToTravel, double speedInchesPerSecond) {
		// TODO
	}
	
	// Provide a default value for travel speed
	public void travelDistance(double inchesToTravel) {
		travelDistance(inchesToTravel, DEFAULT_SPEED_INCHES_PER_S);
	}
	
	// Returns true if and only if the drive pod has achieved the distance commanded by
	// the most recent call to travelDistance()
	public boolean hasFinishedTravelingDistanceCommanded() {
		// TODO
		return true;
	}
}
