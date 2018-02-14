package org.usfirst.frc.team95.robot.strategies;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team95.robot.FieldSide;
import org.usfirst.frc.team95.robot.Robot;

public abstract class Strategy extends CommandGroup {
	
	public static final String DESCRIPTION = "Make a description of your strategy";

	/**
	 * After calling this method, this strategy should be ready to run
	 * as a real command group.  Your strategy must override this method.
	 * 
	 * Note that this method must run quickly; don't do time-consuming processing here.
	 * 
	 * @param whichSideOfTheNearSwitchIsOurColor
	 * @param whichSideOfTheScaleIsOurColor
	 * @param whichSideOfTheFarSwitchIsOurColor
	 * @param robotStartingPosition
	 */
	public abstract void AdjustStrategy(FieldSide whichSideOfTheNearSwitchIsOurColor,
			FieldSide whichSideOfTheScaleIsOurColor,
			Robot.StartPosition robotStartingPosition);
}
