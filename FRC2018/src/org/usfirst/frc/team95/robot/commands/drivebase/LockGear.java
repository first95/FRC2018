package org.usfirst.frc.team95.robot.commands.drivebase;

import org.usfirst.frc.team95.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LockGear extends Command{

	private boolean m_lockGear;
	private static final int LOCK_LOW_GEAR = -1;
	private static final int LOCK_HIGH_GEAR = 1;
	
	public LockGear(boolean lockHighGear) {
		requires(Robot.drivebase);
		m_lockGear = lockHighGear;
	}
	
	@Override
	protected void initialize() {
		super.initialize();
		
		if(m_lockGear) {
			Robot.drivebase.lockGear(LOCK_HIGH_GEAR);
		}
		else {
			Robot.drivebase.lockGear(LOCK_LOW_GEAR);
		}
		
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
