package org.usfirst.frc.team95.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class FacePusher extends Subsystem
{
	private Solenoid piston = new Solenoid(3);
	
	@Override
	protected void initDefaultCommand()
	{
		// TODO Auto-generated method stub
		
	}
	
	public void pushOrPull(boolean extend)
	{
		piston.set(extend);
	}

	public void log() {
		// TODO Auto-generated method stub
		
	}

}
