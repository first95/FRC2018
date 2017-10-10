package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.TankDriveWithJoystick;
import org.usfirst.frc.team95.robot.components.DrivePod;

/**
 * The DriveBase subsystem incorporates the sensors and actuators attached to
 * the robot's chassis. These include two 3-motor drive pods.
 */
public class DriveBase extends Subsystem {
	private final double DEFAULT_TRAVEL_SPEED_INCHES_PER_S = 20;
	private final double DEFAULT_PIVOT_SPEED_RADS_PER_S = Math.PI;
	private DrivePod leftPod, rightPod;
	
	public DriveBase() {
		super();

		leftPod  = new DrivePod("Left",  1, 2, 3, false);
		rightPod = new DrivePod("Right", 4, 5, 6, false); // TODO: one of these may need to be reversed
	}

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new TankDriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		leftPod.log();
		rightPod.log();
	}

	/**
	 * Tank style driving for the DriveTrain.
	 * 
	 * @param left
	 *            Speed in range [-1,1]
	 * @param right
	 *            Speed in range [-1,1]
	 */
	public void drive(double left, double right) {
		tank(left, right);
	}

	/**
	 * @param joy
	 *            The ps3 style joystick to use to drive tank style.
	 */
	public void drive(Joystick joy) {
		drive(-joy.getY(), -joy.getAxis(AxisType.kThrottle));
	}

	/**
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
//		return gyro.getAngle();
		return 0;
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		leftPod.reset();
		rightPod.reset();
	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		// TODO: Some of the commands call this in order to travel a set distance.
		// We want to move that functionality into this class instead.
//		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return (0.0);
	}

	/**
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
//		return rangefinder.getAverageVoltage();
		return 0.0;
	}
	
	public boolean onTarget() {
		return leftPod.isOnTarget() && rightPod.isOnTarget();
	}
	
	// Command that the robot should travel a specific distance along the carpet.
	// Call this once to command distance - do not call repeatedly, as this will reset the
	// distance remaining.
	public void travelStraight(double inchesToTravel, double speedInchesPerSecond) {
		leftPod. travelDistance(inchesToTravel, speedInchesPerSecond);
		rightPod.travelDistance(inchesToTravel, speedInchesPerSecond);
	}
	
	// Provide a default value for travel speed
	public void travelStraight(double inchesToTravel) {
		travelStraight(inchesToTravel, DEFAULT_TRAVEL_SPEED_INCHES_PER_S);
	}
		
	// Command that the robot should travel a specific distance along the carpet.
	// Call this once to command distance - do not call repeatedly, as this will reset the
	// distance remaining.
	public void pivotRadians(double radiansToPivot, double speedRadiansPerSecond) {
		// TODO: Command left and right pods to go opposite directions for a given speed and distance
//		leftPod. travelDistance(inchesToTravel, speedInchesPerSecond);
//		rightPod.travelDistance(inchesToTravel, speedInchesPerSecond);
	}

	// Provide default value
	public void pivotRadians(double radiansToPivot) {
		pivotRadians(radiansToPivot, DEFAULT_PIVOT_SPEED_RADS_PER_S);
	}
	
	public void travelSweepingTurn(double radiansToTurn, double turningRadius, double speedRadiansPerSecond ) {
		// TODO: Command each side of the robot to sweep out the appropriate arc
	}
	
	// Corresponded to the Drive class in the 2017 code
	public void tank(double leftsp, double rightsp)
		{
			leftPod.setThrottle(-leftsp);
			rightPod.setThrottle(rightsp);
		}

	public void arcade(double forward, double spin)
		{
			tank(forward - spin, forward + spin);
		}

	public void halfArcade(double forward, double spin)
		{
			tank((forward - spin) / 2, (forward + spin) / 2);
		}

	public void arcade(Joystick stick, boolean twostick)
		{
			double y = stick.getY();
			double x;
			if (twostick)
				{
					x = stick.getRawAxis(4);
				}
			else
				{
					x = stick.getX();
				}

			if (Math.abs(y) <= Constants.joystickDeadbandV)
				{
					y = 0;
				}

			if (Math.abs(x) <= Constants.joystickDeadbandH)
				{
					x = 0;
				}

			// "Exponential" drive, where the movements are more sensitive during slow movement,
			// permitting easier fine control
			x = Math.pow(x, 3);
			y = Math.pow(y, 3);
			arcade(y, x);
		}

	public void halfArcade(Joystick stick, boolean twostick)
		{
			double y = stick.getY();
			double x;
			if (twostick)
				{
					x = stick.getRawAxis(4);
				}
			else
				{
					x = stick.getX();
				}
			if (Math.abs(y) <= Constants.joystickDeadbandV)
				{
					y = 0;
				}

			if (Math.abs(x) <= Constants.joystickDeadbandH)
				{
					x = 0;
				}

			// "Exponential" drive, where the movements are more sensitive during slow movement,
			// permitting easier fine control
			x = Math.pow(x, 3);
			y = Math.pow(y, 3);
			halfArcade(y, x);
		}
}
