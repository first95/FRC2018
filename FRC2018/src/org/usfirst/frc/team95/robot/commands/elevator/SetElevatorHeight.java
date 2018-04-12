package org.usfirst.frc.team95.robot.commands.elevator;

import org.usfirst.frc.team95.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class SetElevatorHeight extends Command {
	// Note that a different set of constants is used in manual control
	public static double FLOOR_HEIGHT_FEET = 0.0;
	public static double SWITCH_SCORE_HEIGHT_FEET = 2.0;
	public static double SCALE_SCORE_LOW_HEIGHT_FEET = 5;
	public static double SCALE_SCORE_HIGH_HEIGHT_FEET = 5.5; //+0.5 removed due to issues discovered in practice match;
	public enum ElevatorHoldPoint {
		FLOOR,            // Positioned at its lowest position
		SWITCH_SCORE,     // Positioned to hold a cube above the fence around the Switch
		SCALE_SCORE_HIGH, // Positioned at the lowest  sensible point to score a cube on the Scale
		SCALE_SCORE_LOW,  // Positioned at the highest sensible point to score a cube on the Scale
	};
	
	private ElevatorHoldPoint targetPoint = null;
	private Double targetFeet = 0.0;
	
	public SetElevatorHeight(ElevatorHoldPoint targetHoldPoint) {
		// This method is run once during robot startup
		requires(Robot.elevator);
		targetPoint = targetHoldPoint;
	}

	public SetElevatorHeight(double targetFeet) {
		// This method is run once during robot startup
		requires(Robot.elevator);
		this.targetFeet = targetFeet;
	}

	@Override
	public synchronized void initialize() {
		// This method is called once when the command is activated
		if(targetPoint != null) {
			seekHoldPoint(targetPoint);
		} else {
			Robot.elevator.setElevatorHeight(targetFeet);
		}
	}
	
	@Override
	protected void execute() {
		// This method is called every iteration
		
		// Nothing needed; we did everything we needed in initialize()
	}
	
	@Override
	public synchronized void cancel() {
		// Cancel any position seeking
		Robot.elevator.stopMotor();
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.elevator.isOnTarget();
	}
	
	/**
	 * Seek a predefined point.
	 * The elevator will seek and hold this point until it loses power
	 * or receives a new command.  It will remember to seek this point when disabled,
	 * unless a command is given to cause it to forget.
	 * @param point - which point to seek, see ElevatorHoldPoint
	 */
	private void seekHoldPoint(ElevatorHoldPoint point) {
		double desiredHeightFeet = 0;
		switch(point) {
		case FLOOR:
			desiredHeightFeet = FLOOR_HEIGHT_FEET;
			break;
		case SCALE_SCORE_HIGH:
			desiredHeightFeet = SCALE_SCORE_HIGH_HEIGHT_FEET;
			break;
		case SCALE_SCORE_LOW:
			desiredHeightFeet = SCALE_SCORE_LOW_HEIGHT_FEET;
			break;
		case SWITCH_SCORE:
			desiredHeightFeet = SWITCH_SCORE_HEIGHT_FEET;
			break;
		default:
			break;
		}
		
		Robot.elevator.setElevatorHeight(desiredHeightFeet);
	}
}
