
package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team95.robot.components.DrivePod;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

/**
 * The DriveBase subsystem incorporates the sensors and actuators attached to
 * the robot's chassis. These include two 3-motor drive pods.
 */
public class DriveBase extends Subsystem {
	// private final double DEFAULT_TRAVEL_SPEED_INCHES_PER_S = 20;
	private final double DEFAULT_PIVOT_SPEED_RADS_PER_S = Math.PI;
	private final double DEFAULT_PIVOT_SPEED_DEGREE_PER_S = 57.2958;
	private DrivePod leftPod, rightPod;
	private SolenoidI shifter;

	public DriveBase() {
		super();

		leftPod = new DrivePod("Left", Constants.LEFT_LEAD, Constants.LEFT_F1, Constants.LEFT_F2);
		rightPod = new DrivePod("Right", Constants.RIGHT_LEAD, Constants.RIGHT_F1, Constants.RIGHT_F2);
		shifter = new SolenoidWrapper(Constants.SHIFTER_SOLENOID_NUM);
	}

	/**
	 * When no other command is running let the operator drive around using the PS3
	 * joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveWithJoystick());
	}

	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		leftPod.log();
		rightPod.log();

		SmartDashboard.putNumber("leftDriveEncoder Value:", leftPod.getQuadEncPos());
		SmartDashboard.putNumber("rightDriveEncoder Value:", rightPod.getQuadEncPos());
		SmartDashboard.putNumber("leftDriveCurrent:", leftPod.getLeadCurrent());
		SmartDashboard.putNumber("RightDriveCurrent:", rightPod.getLeadCurrent());

		SmartDashboard.putNumber("Left Pod Velocity:", leftPod.getEncoderVelocity());
		SmartDashboard.putNumber("Right Pod Velocity:", rightPod.getEncoderVelocity());
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
	 * @return The robots heading in degrees.
	 */
	public double getHeading() {
		// return gyro.getAngle();
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
		return (leftPod.getQuadEncPos() + rightPod.getQuadEncPos()) / 2;
	}

	/**
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
		// return rangefinder.getAverageVoltage();
		return 0.0;
	}

	public boolean onTarget() {
		return leftPod.isOnTarget() && rightPod.isOnTarget();
	}

	// Command that the robot should travel a specific distance along the carpet.
	// Call this once to command distance - do not call repeatedly, as this will
	// reset the
	// distance remaining.
	public void travelStraight(double inchesToTravel) {
		leftPod.setCLPosition(-inchesToTravel);
		rightPod.setCLPosition(inchesToTravel);
	}

	// Talon Brake system
	public void brake(boolean isEnabled) {
		leftPod.enableBrakeMode(isEnabled);
		rightPod.enableBrakeMode(isEnabled);
	}

	// Command that the robot should travel a specific distance along the carpet.
	// Call this once to command distance - do not call repeatedly, as this will
	// reset the
	// distance remaining.
	public void pivotRadians(double radiansToPivot, double speedRadiansPerSecond) {
		// TODO: Command left and right pods to go opposite directions for a given speed
		// and distance
		// leftPod. travelDistance(inchesToTravel, speedInchesPerSecond);
		// rightPod.travelDistance(inchesToTravel, speedInchesPerSecond);
	}

	// Provide default value
	public void pivotRadians(double radiansToPivot) {
		pivotRadians(radiansToPivot, DEFAULT_PIVOT_SPEED_RADS_PER_S);
	}

	public void travelSweepingTurn(double radiansToTurn, double turningRadius, double speedRadiansPerSecond) {
		// TODO: Command each side of the robot to sweep out the appropriate arc
	}

	// Corresponded to the Drive class in the 2017 code
	public void tank(double leftsp, double rightsp) {
		leftPod.setThrottle(-leftsp);
		rightPod.setThrottle(rightsp);
	}

	public void arcade(double forward, double spin) {
		tank(forward - spin, forward + spin);
	}

	public void arcade() {
		double y = Robot.oi.getForwardAxis();
		double x = Robot.oi.getTurnAxis();

		// "Exponential" drive, where the movements are more sensitive during slow
		// movement,
		// permitting easier fine control
		x = Math.pow(x, 3);
		y = Math.pow(y, 3);
		arcade(y, x);
	}

	public double getLeftSpeed() {
		return leftPod.getEncoderVelocity();
	}

	public double getRightSpeed() {
		return rightPod.getEncoderVelocity();
	}

	public double getLeftEncoderPos() {

		return leftPod.getQuadEncPos();
	}

	public double getRightEncoderPos() {

		return rightPod.getQuadEncPos();
	}

	public void setGear(boolean isHighGear) {
		shifter.set(isHighGear);
	}

	public void pullPidConstantsFromSmartDash() {
		leftPod.pullPidConstantsFromSmartDash();
		rightPod.pullPidConstantsFromSmartDash();
	}
}
