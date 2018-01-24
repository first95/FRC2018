package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class FacePusherCommand extends Command
{
	private boolean extend;
	public void pushOrPullFace(boolean extend)
	{
		//requires(Robot.facepusher);
		this.extend = extend;
	}
	
	protected void initialize()
	{
		//Robot.facepusher.pushOrPull(extend);
	}
	
	protected void execute()
	{
		
	}

	@Override
	protected boolean isFinished()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
