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
	private SpeedController frontLeftMotor = new Talon(1);
	private SpeedController rearLeftMotor = new Talon(2);
	private SpeedController frontRightMotor = new Talon(3);
	private SpeedController rearRightMotor = new Talon(4);

	private RobotDrive drive = new RobotDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

	private Encoder leftEncoder = new Encoder(1, 2);
	private Encoder rightEncoder = new Encoder(3, 4);
	private AnalogInput rangefinder = new AnalogInput(6);
	private AnalogGyro gyro = new AnalogGyro(1);

	private DrivePod leftPod, rightPod;
	
	public DriveBase() {
		super();

		leftPod = new DrivePod(1, 2, 3, false);
		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		if (Robot.isReal()) {
			leftEncoder.setDistancePerPulse(0.042);
			rightEncoder.setDistancePerPulse(0.042);
		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			leftEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
			rightEncoder.setDistancePerPulse((4.0 / 12.0 * Math.PI) / 360.0);
		}

		// Let's show everything on the LiveWindow
		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Talon) frontLeftMotor);
		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) rearLeftMotor);
		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) frontRightMotor);
		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) rearRightMotor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
		LiveWindow.addSensor("Drive Train", "Rangefinder", rangefinder);
		LiveWindow.addSensor("Drive Train", "Gyro", gyro);
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
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
		SmartDashboard.putNumber("Gyro", gyro.getAngle());
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
		drive.tankDrive(left, right);
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
		return gyro.getAngle();
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		gyro.reset();
		leftEncoder.reset();
		rightEncoder.reset();
	}

	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
	}

	/**
	 * @return The distance to the obstacle detected by the rangefinder.
	 */
	public double getDistanceToObstacle() {
		// Really meters in simulation since it's a rangefinder...
		return rangefinder.getAverageVoltage();
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
