package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Robot;
//import org.usfirst.frc.team95.robot.commands.armSwinger.ManuallyControlArmSwinger;
import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmSwinger extends Subsystem
{
	private IMotorControllerEnhanced armSwinger;
	
	private double K_P = 0;
	private double K_I = 0;
	private double K_D = 0;
	private static final int I_ZONE = 200;
	private final String pLabel = "Swinger P";
	private final String iLabel = "Swinger I";
	private final String dLabel = "Swinger D";
	
	public ArmSwinger()
	{
		super();
		armSwinger = new AdjustedTalon(Constants.ARM_SWINGER_TALON);
		
		armSwinger.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
		armSwinger.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
		armSwinger.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
		
		armSwinger.config_IntegralZone(Constants.PID_IDX, I_ZONE, Constants.CAN_TIMEOUT_MS);
		
		 SmartDashboard.putNumber(pLabel, K_P);
		 SmartDashboard.putNumber(iLabel, K_I);
		 SmartDashboard.putNumber(dLabel, K_D);
	}
	public void setCurrentPosToZero()
	{
			 armSwinger.setSelectedSensorPosition(0, Constants.PID_IDX,Constants.CAN_TIMEOUT_MS);
	}
	public void brake(boolean isEnabled)
	{
			 armSwinger.setNeutralMode(isEnabled ? NeutralMode.Brake : NeutralMode.Coast);
	}
	@Override
	protected void initDefaultCommand()
	{
		//setDefaultCommand(new ManuallyControlArmSwinger());
	}
	public void log()
	{
		
	}
	public void setArmSpeed(double value)
	{
		armSwinger.set(ControlMode.PercentOutput, value);
	}
	public void stopMotor()
	{
		armSwinger.set(ControlMode.PercentOutput, 0.0);
	}
	public void pullPidConstantsFromSmartDash()
	{
		 // Retrieve
		 K_P = SmartDashboard.getNumber(pLabel, K_P);
		 K_I = SmartDashboard.getNumber(iLabel, K_I);
		 K_D = SmartDashboard.getNumber(dLabel, K_D);
	
		 // Apply
		 armSwinger.config_kP(Constants.PID_IDX, K_P, Constants.CAN_TIMEOUT_MS);
		 armSwinger.config_kI(Constants.PID_IDX, K_I, Constants.CAN_TIMEOUT_MS);
		 armSwinger.config_kD(Constants.PID_IDX, K_D, Constants.CAN_TIMEOUT_MS);
	}

}
