
package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.drivebase.ManuallyControlDrivebase;
import org.usfirst.frc.team95.robot.components.DrivePod;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

/**
 * The DriveBase subsystem incorporates the sensors and actuators attached to
 * the robot's chassis. These include two 3-motor drive pods.
 */
public class DriveBase extends Subsystem {
	// This is how much extra we command the pods to move to account for slippage
	private final double PIVOT_FUDGE_FACTOR = 1.5; 
	// The speed at which we want the center of the robot to travel
	private final double SWEEPER_TURN_SPEED_INCHES_PER_SECOND = 3.5*12.0; 
	private DrivePod leftPod, rightPod;
	private SolenoidI shifter;

	public DriveBase() {
		super();

		leftPod = new DrivePod("Left", Constants.LEFT_LEAD, Constants.LEFT_F1, Constants.LEFT_F2);
		rightPod = new DrivePod("Right", Constants.RIGHT_LEAD, Constants.RIGHT_F1, Constants.RIGHT_F2);
		shifter = new SolenoidWrapper(Constants.SHIFTER_SOLENOID_NUM);
	}

	/**
	 * When no other command is running let the operator drive around using the
	 * PS3 joystick.
	 */
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlDrivebase());
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
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		// TODO: Some of the commands call this in order to travel a set
		// distance.
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

	/**
	 * Command that the robot should travel a specific distance along the
	 * carpet.  Call this once to command distance - do not call repeatedly, as
	 * this will reset the distance remaining.
	 * 
	 * @param inchesToTravel - number of inches forward to travel
	 */
	public void travelStraight(double inchesToTravel) {
		leftPod.setCLPosition(-inchesToTravel);
		rightPod.setCLPosition(inchesToTravel);
	}
	/**
	 * Command that the robot should travel a specific distance along the
	 * carpet.  Call this once to command distance - do not call repeatedly, as
	 * this will reset the distance remaining.
	 * 
	 * @param inchesToTravel - number of inches forward to travel
	 * @param inchesPerSecond - speed at which to travel
	 */
	public void travelStraight(double inchesPerSecond, double inchesToTravel) {
		leftPod.driveForDistanceAtSpeed(-inchesPerSecond, -inchesToTravel);
		rightPod.driveForDistanceAtSpeed(inchesPerSecond, inchesToTravel);
	}
	// Talon Brake system
	public void brake(boolean isEnabled) {
		leftPod.enableBrakeMode(isEnabled);
		rightPod.enableBrakeMode(isEnabled);
	}

	public void pivotDegreesClockwise(double degreesToPivotCw) {
		double leftDistanceInches = (degreesToPivotCw / 360.0) * Math.PI * Constants.ROBOT_WHEELBASE_WIDTH_INCHES;
		double rightDistanceInches = leftDistanceInches;
		leftDistanceInches *= PIVOT_FUDGE_FACTOR;
		rightDistanceInches *= PIVOT_FUDGE_FACTOR;
		leftPod.setCLPosition(leftDistanceInches);
		rightPod.setCLPosition(rightDistanceInches);
	}

	/**
	 * Cause the robot's center to sweep out an arc with given radius and angle.
	 * A positive clockwise angle is forward and to the right, a negative
	 * clockwise angle is forward and to the left.
	 * 
	 * This does not take into account the drivebase's tendency toward straight
	 * turns.
	 * 
	 * @param degreesToTurnCw
	 * @param turnRadiusInches
	 */
	public void travelSweepingTurnForward(double degreesToTurnCw, double turnRadiusInches) {
		double leftDistanceInches;
		double rightDistanceInches;
		double leftSpeedInchesPerSecond  = SWEEPER_TURN_SPEED_INCHES_PER_SECOND;
		double rightSpeedInchesPerSecond = SWEEPER_TURN_SPEED_INCHES_PER_SECOND;

		double fractionOfAFullCircumference = Math.abs(degreesToTurnCw / 360.0);
		double ratioOfSmallerToLarger =
				  (turnRadiusInches - Constants.ROBOT_WHEELBASE_WIDTH_INCHES / 2.0)
				/ (turnRadiusInches + Constants.ROBOT_WHEELBASE_WIDTH_INCHES / 2.0);

		if (degreesToTurnCw > 0) {
			// Forward and to the right - CW
			rightSpeedInchesPerSecond *= ratioOfSmallerToLarger;
			leftDistanceInches = fractionOfAFullCircumference * Math.PI
					* 2.0 * (turnRadiusInches + Constants.ROBOT_WHEELBASE_WIDTH_INCHES / 2.0);
			rightDistanceInches = fractionOfAFullCircumference * Math.PI
					* 2.0 * (turnRadiusInches - Constants.ROBOT_WHEELBASE_WIDTH_INCHES / 2.0);
			rightDistanceInches *= -1.0; // Right pod is backwards from the left
		} else {
			// Forward and to the left - CCW
			leftSpeedInchesPerSecond *= ratioOfSmallerToLarger;
			leftDistanceInches = fractionOfAFullCircumference * Math.PI
					* 2.0 * (turnRadiusInches - Constants.ROBOT_WHEELBASE_WIDTH_INCHES / 2.0);
			rightDistanceInches = fractionOfAFullCircumference * Math.PI
					* 2.0 * (turnRadiusInches + Constants.ROBOT_WHEELBASE_WIDTH_INCHES / 2.0);
			rightDistanceInches *= -1.0; // Right pod is backwards from the left
		}

		leftPod. driveForDistanceAtSpeed(leftSpeedInchesPerSecond, leftDistanceInches);
		rightPod.driveForDistanceAtSpeed(rightSpeedInchesPerSecond, rightDistanceInches);
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

		// "Exponential" drive, where the movements are more sensitive during
		// slow
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
