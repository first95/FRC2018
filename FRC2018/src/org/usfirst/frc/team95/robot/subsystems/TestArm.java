package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.commands.TestArmSet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestArm extends Subsystem {
	private TalonSRX motor = new TalonSRX(7);
	
	public static final double ENCODER_TICKS_PER_ARM_REV = 8192.0;
	public static final int PID_IDX = 0; // The Talons support multiple cascaded PID loops.  Here we're only using one.
	public static final int CAN_TIMEOUT_MS = 10; // Timeout for each operation.
	
	// Velocity control constants, determined by following section 12.4 in the software reference manual.
	public static final double K_F = 0.24224484963296234904;
	public static final double K_P = 0.113333 * 2.0;
	public static final double K_I = 0;
	public static final double K_D = 0;
	
	public TestArm() {
		super();
		
		// Configure Talon
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, PID_IDX, CAN_TIMEOUT_MS);
		motor.config_kF(PID_IDX, K_F, CAN_TIMEOUT_MS);
		motor.config_kP(PID_IDX, K_P, CAN_TIMEOUT_MS);
		motor.config_kI(PID_IDX, K_I, CAN_TIMEOUT_MS);
		motor.config_kD(PID_IDX, K_D, CAN_TIMEOUT_MS);
	}
	
	@Override
	protected void initDefaultCommand() {
		System.out.println("initDefaultCommand");
		setDefaultCommand(new TestArmSet());
	}
	
	/**
	 * 
	 * @param value - the amount of speed/power or position.  Expected to be between -1 and 1.
	 */
	@SuppressWarnings("unused")
	public void setMotor(double value) {
		final int STEP = 1; // Step in the list of steps to determine the PID constants.
		
		if(0 == STEP) {
			motor.set(ControlMode.PercentOutput, value);
			System.out.println("Set: " + value + "\t Speed (sensor units per 100ms): " +  motor.getSelectedSensorVelocity(PID_IDX));
		} else if (1 == STEP) {
			double target_nupt = value * 2000.0; // In native units per 100ms
			motor.set(ControlMode.Velocity, target_nupt);
			System.out.println("Target: " + target_nupt + "\t Measured: " +
					motor.getSelectedSensorVelocity(PID_IDX) + "\t Err: " + motor.getClosedLoopError(PID_IDX));
		}
	}
	
	public void updateSmartDash() {
		SmartDashboard.putNumber("Position", motor.getSelectedSensorPosition(PID_IDX));
		SmartDashboard.putNumber("Velocity", motor.getSelectedSensorVelocity(PID_IDX));
		SmartDashboard.putNumber("Target",   motor.getClosedLoopTarget(PID_IDX));
		SmartDashboard.putNumber("Error",    motor.getClosedLoopError(PID_IDX));
	}

}
