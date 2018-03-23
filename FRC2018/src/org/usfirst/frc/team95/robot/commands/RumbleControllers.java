package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class RumbleControllers extends Command{
	
	boolean m_state;
	
	public RumbleControllers(boolean state) {
		m_state = state;
	}
	
	@Override
	public void initialize() {
		Robot.oi.setDriverRumble(m_state);
		Robot.oi.setWeaponierRumble(m_state);
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
