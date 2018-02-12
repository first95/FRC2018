package org.usfirst.frc.team95.robot.strategies;

import org.usfirst.frc.team95.robot.FieldSide;

public class SitStill extends Strategy {
	
	// This strategy does nothing.

	
	public static final String DESCRIPTION = "Do nothing.";
	
	public SitStill() {
		// Add all the moves in AdjustStrategy
	}

	@Override
	public void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor, FieldSide whichSideOfTheScaleIsOurColor,
			FieldSide whichSideOfTheFarSwitchIsOurColor, FieldSide robotStartingPosition) {
		// Do nothing
	}

}
