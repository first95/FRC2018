package org.usfirst.frc.team95.robot.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team95.robot.Robot;


public class Climber extends Subsystem
{
	private CANTalon winchLeft = new CANTalon(7);
	private CANTalon winchRight = new CANTalon(8);
	
	public void climb (double input)
	{
		winchLeft.set(input);
		winchRight.set(-input);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void log() {
		// TODO Auto-generated method stub
		
	}
}
