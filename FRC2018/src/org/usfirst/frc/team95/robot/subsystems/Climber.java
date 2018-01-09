package org.usfirst.frc.team95.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team95.robot.commands.ClimberCommand;


public class Climber extends Subsystem
{
	private TalonSRX winchLeft = new TalonSRX(7);
	private TalonSRX winchRight = new TalonSRX(8);
	
	public Climber ()
	{
		super();
	}
	
	public void climb (double input)
	{
		winchLeft.set(ControlMode.PercentOutput, input);
		winchRight.set(ControlMode.PercentOutput, -input);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ClimberCommand());
	}

	public void log() {
		// TODO Auto-generated method stub
		
	}
}
