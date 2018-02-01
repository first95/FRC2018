
package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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

	private boolean isHighGear = false;

	private final double DEFAULT_TRAVEL_SPEED_INCHES_PER_S = 20;
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
		// return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
		return (0.0);
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
	public void travelStraight(double inchesToTravel, double speedInchesPerSecond) {
		leftPod.travelDistance(inchesToTravel, speedInchesPerSecond);
		rightPod.travelDistance(inchesToTravel, speedInchesPerSecond);
	}

	// Provide a default value for travel speed
	public void travelStraight(double inchesToTravel) {
		travelStraight(inchesToTravel, DEFAULT_TRAVEL_SPEED_INCHES_PER_S);
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
	public boolean pivotDegrees(double degreesToPivot, double speedDegreesPerSecond) {

		double m_degreesToPivot, m_speedDegreesPerSecond, startPos, finalPos, startingSpeed, finalSpeed, error, P;
		boolean complete;
		boolean rotating = true;

		m_degreesToPivot = degreesToPivot;
		m_speedDegreesPerSecond = speedDegreesPerSecond;
		P = 0.35;

		if (m_degreesToPivot >= 0) {
			startPos = getLeftEncoderPos();
		} else {
			startPos = getRightEncoderPos();
		}

		finalPos = startPos + (Constants.ENCODER_TICKS_PER_RADIAN * degreesToPivot);
		startingSpeed = 0;

		if (degreesToPivot >= 0) {
			error = finalPos - getLeftEncoderPos();
		} else {
			error = finalPos - getRightEncoderPos();
		}

		speedDegreesPerSecond = P * error;
		complete = false;

		while (rotating) {
			if (Math.abs(error) <= Constants.ENCODER_TICKS_PER_RADIAN * .04) {
				complete = true;
				rotating = false;
			} else {
				if (degreesToPivot >= 0) {
					error = finalPos - getLeftEncoderPos();
				} else {
					error = finalPos - getRightEncoderPos();
				}

				speedDegreesPerSecond = (P * error) / 200;// divide to make speed value reasonable
				if (speedDegreesPerSecond > .5) {
					speedDegreesPerSecond = .5;
				} else if (speedDegreesPerSecond < -.5) {
					speedDegreesPerSecond = -.5;
				}

				if (speedDegreesPerSecond > (startingSpeed + .08)) {
					speedDegreesPerSecond = startingSpeed + .08;
				}

				tank(-speedDegreesPerSecond, speedDegreesPerSecond);

				startingSpeed = speedDegreesPerSecond;
			}
		}

		return complete;
	}

	// Provide default value
	public void pivotDegrees(double degreesToPivot) {
		pivotDegrees(degreesToPivot, DEFAULT_PIVOT_SPEED_DEGREE_PER_S);
	}

	public void pivotRadians(double radiansToPivot, double speedRadiansPerSecond) {
		// TODO: Command left and right pods to go opposite directions for a given speed
		// and distance
		// leftPod. travelDistance(inchesToTravel, speedInchesPerSecond);
		// rightPod.travelDistance(inchesToTravel, speedInchesPerSecond);
	}

	// Provide default value
	public void pivotRadians(double radiansToPivot) {
		pivotDegrees(radiansToPivot, DEFAULT_PIVOT_SPEED_RADS_PER_S);
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

	public double getLeftEncoderPos() {

		return leftPod.getQuadEncPos();
	}

	public double getRightEncoderPos() {

		return rightPod.getQuadEncPos();
	}

	public void setGear(boolean isHighGear) {
		this.isHighGear = isHighGear;
		shifter.set(isHighGear);
	}
}
