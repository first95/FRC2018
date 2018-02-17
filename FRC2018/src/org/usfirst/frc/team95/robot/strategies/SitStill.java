package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;

public class SitStill extends Strategy {
	
	// This strategy does nothing.

	
	public static final String DESCRIPTION = "Do nothing.";
	
	public SitStill() {
		// Add all the moves in AdjustStrategy
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor,
			FieldSide whichSideOfTheScaleIsOurColor,
			Robot.StartPosition robotStartingPosition) {
		// Do nothing
	}

}
