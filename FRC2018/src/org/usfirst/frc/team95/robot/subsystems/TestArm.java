package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.commands.SlackTestArm;
import org.usfirst.frc.team95.robot.commands.TestArmSet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestArm extends Subsystem {
	private TalonSRX motor = new TalonSRX(7);
	public static final double ENCODER_TICKS_PER_ARM_REV = 4096 * 2;
	public static final int PID_IDX = 0; // The Talons support multiple cascaded PID loops.  Here we're only using one.
	public static final int CAN_TIMEOUT_MS = 10; // Timeout for each operation.
	
	// Velocity control constants, determined by following section 12.4 in the software reference manual.
//	public static final double K_F = 0.24224484963296234904; // = 1023/4223, where 4223 was the velocity measured when the motor was full throttle
	public static final double K_F = 0.0; // Don't use in position mode.
	public static final double K_P = 0.4 * 1023.0 / 900.0; // Respond to an error of 900 with 40% throttle
	public static final double K_I = 0.01 * K_P;
	public static final double K_D = 20.0 * K_P;
	public static final int I_ZONE = 100; // In closed loop error units
	
	private double twiddle = 0; // This is to workaround a super silly feature in SmartDashboard
	
	public TestArm() {
		super();
		
		// Configure Talon
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, PID_IDX, CAN_TIMEOUT_MS);
		motor.config_kF(PID_IDX, K_F, CAN_TIMEOUT_MS);
		motor.config_kP(PID_IDX, K_P, CAN_TIMEOUT_MS);
		motor.config_kI(PID_IDX, K_I, CAN_TIMEOUT_MS);
		motor.config_kD(PID_IDX, K_D, CAN_TIMEOUT_MS);
		
		// Prevent Integral Windup.
		// Whenever the control loop error is outside this zone, zero out the integrator.
		motor.config_IntegralZone(PID_IDX, I_ZONE, CAN_TIMEOUT_MS);
		
		// Figure out where it thinks it is now
//		initialArmPos = motor.getSelectedSensorPosition(PID_IDX);
		
		// We'll be treating this spot as the zero.
		// SAFETY NOTE: this is actually really important. (when using position mode)
		// The talon tracks position changes as long as it has power, no matter
		// how many times you restart the code.  You can easily turn a motor a zillion
		// times, and the talon is counting each and every one of those revolutions.
		// If you then command the motor to see position 0, it will make all haste 
		// to turn it back as many revolutions as you've turned the shaft since power-on.
		motor.setSelectedSensorPosition(0, PID_IDX, CAN_TIMEOUT_MS);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TestArmSet());
	}
	
	/**
	 * 
	 * @param value - the amount of speed/power or position.  Expected to be between -1 and 1.
	 */
	@SuppressWarnings("unused")
	public void setMotor(double value) {
		final int STEP = 2; // Step in the list of steps to determine the PID constants.
		
		if(0 == STEP) {
			motor.set(ControlMode.PercentOutput, value);
			System.out.println("Set: " + value + "\t Speed (sensor units per 100ms): " +  motor.getSelectedSensorVelocity(PID_IDX));
		} else if (1 == STEP) {
			double target_nupt = value * 2000.0; // In native units per 100ms
			motor.set(ControlMode.Velocity, target_nupt);
			System.out.println("Target: " + target_nupt + "\t Measured: " +
					motor.getSelectedSensorVelocity(PID_IDX) + "\t Err: " + motor.getClosedLoopError(PID_IDX));
			SmartDashboard.putNumber("Target",   motor.getClosedLoopTarget(PID_IDX) + twiddle);
			SmartDashboard.putNumber("Error",    motor.getClosedLoopError(PID_IDX)  + twiddle);
			SmartDashboard.putNumber("Position", motor.getSelectedSensorPosition(PID_IDX) + twiddle);
			SmartDashboard.putNumber("Velocity", motor.getSelectedSensorVelocity(PID_IDX) + twiddle);
		} else if (2 == STEP) {
			double target_pos = value * ENCODER_TICKS_PER_ARM_REV / 2.0; // In native units
			motor.set(ControlMode.Position, target_pos);
			System.out.println("Target: " + target_pos + "\t Measured: " +
					motor.getSelectedSensorPosition(PID_IDX) + "\t Err: " + motor.getClosedLoopError(PID_IDX));
			SmartDashboard.putNumber("Target",   motor.getClosedLoopTarget(PID_IDX) + twiddle);
			SmartDashboard.putNumber("Error",    motor.getClosedLoopError(PID_IDX) + twiddle);
			SmartDashboard.putNumber("Position", motor.getSelectedSensorPosition(PID_IDX) + twiddle);
			SmartDashboard.putNumber("Velocity", motor.getSelectedSensorVelocity(PID_IDX) + twiddle);
		}
		
		// The SmartDashboard's graph utility doesn't add a new sample unless the value has changed since
		// the last iteration.  So make sure all the values change every iteration.
		if(twiddle > 0) {
			twiddle = 0.0;
		} else {
			twiddle = 0.0000000001;
		}
	}
	
	public void stopMotor() {
		motor.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void updateSmartDash() {
	}

}
