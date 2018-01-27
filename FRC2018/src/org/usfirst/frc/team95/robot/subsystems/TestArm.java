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
	public static final double K_F = 0.0354850853954534691;
	
	public TestArm() {
		super();
		
		// Configure Talon
		motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, PID_IDX, CAN_TIMEOUT_MS);
		motor.config_kF(PID_IDX, K_F, CAN_TIMEOUT_MS);
//		motor.config_kP(0, value, timeoutMs)
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
	public void setMotor(double value) {
		motor.set(ControlMode.PercentOutput, value);
		System.out.println("Set: " + value + "\t Speed: " +  motor.getSelectedSensorVelocity(0));
//		double speed_in_rev_per_second = value * 2.0;
//		double speed_in_ticks_per_100ms = speed_in_rev_per_second * ENCODER_TICKS_PER_ARM_REV / 10.0;
//		System.out.println("Target velocity is: " + speed_in_ticks_per_100ms);
//		motor.set(ControlMode.Velocity, speed_in_ticks_per_100ms);
	}
	
	public void updateSmartDash() {
		SmartDashboard.putNumber("Position", motor.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Velocity", motor.getSelectedSensorVelocity(0));
	}

}
