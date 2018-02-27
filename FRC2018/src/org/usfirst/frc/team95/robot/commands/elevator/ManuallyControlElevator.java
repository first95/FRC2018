package org.usfirst.frc.team95.robot.commands.elevator;

import org.usfirst.frc.team95.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManuallyControlElevator extends Command {
	// Note that a different set of constants is used in autonomous control
	public static double FLOOR_HEIGHT_FEET = 0.0;
	public static double SWITCH_SCORE_HEIGHT_FEET = 2.0;
	public static double SCALE_SCORE_LOW_HEIGHT_FEET = 4.5 - 0.25;
	public static double SCALE_SCORE_MED_HEIGHT_FEET = 5 - 0.25;
	public static double SCALE_SCORE_HIGH_HEIGHT_FEET = 5.5 - 0.25;

	public enum ElevatorHoldPoint {
		FLOOR, // Positioned at its lowest position
		SWITCH_SCORE, // Positioned to hold a cube above the fence around the Switch
		SCALE_SCORE_HIGH, // Positioned at the lowest sensible point to score a cube on the Scale
		SCALE_SCORE_MED, // Positioned at a medium point to score a cube on the Scale
		SCALE_SCORE_LOW, // Positioned at the lowest sensible point to score a cube on the Scale
		HERE, // Not a specific location - indicates holding whatever point the elevator is at
				// now.
	};

	private boolean wasHoldingPresentPositionLastIteration = false;

	public ManuallyControlElevator() {
		// This method is run once during robot startup
		requires(Robot.elevator);
	}

	@Override
	public synchronized void start() {
		// This method is called once when the command is activated
		seekHoldPoint(ElevatorHoldPoint.HERE);
		wasHoldingPresentPositionLastIteration = true;
	}

	@Override
	protected void execute() {
		// This method is called every iteration

		final String ELEV_MODE = "Elevator mode";
		// First priority: Is the user holding down one of the seek buttons?
		if (Robot.oi.isElevatorFloorButtonPressed()) {
			// SmartDashboard.putString(ELEV_MODE, "Seek floor");
			seekHoldPoint(ElevatorHoldPoint.FLOOR);
			wasHoldingPresentPositionLastIteration = false;
		} else if (Robot.oi.isElevatorSwitchScoreButtonPressed()) {
			// SmartDashboard.putString(ELEV_MODE, "Seek switch scoring position");
			seekHoldPoint(ElevatorHoldPoint.SWITCH_SCORE);
			wasHoldingPresentPositionLastIteration = false;
		} else if (Robot.oi.isElevatorScaleScoreLowButtonPressed()) {
			// SmartDashboard.putString(ELEV_MODE, "Seek scale scoring position (low)");
			seekHoldPoint(ElevatorHoldPoint.SCALE_SCORE_LOW);
			wasHoldingPresentPositionLastIteration = false;
		} else if (Robot.oi.isElevatorScaleScoreMedButtonPressed()) {
			// SmartDashboard.putString(ELEV_MODE, "Seek scale scoring position (med)");
			seekHoldPoint(ElevatorHoldPoint.SCALE_SCORE_MED);
			wasHoldingPresentPositionLastIteration = false;
		} else if (Robot.oi.isElevatorScaleScoreHighButtonPressed()) {
			// SmartDashboard.putString(ELEV_MODE, "Seek scale scoring position (high)");
			seekHoldPoint(ElevatorHoldPoint.SCALE_SCORE_HIGH);
			wasHoldingPresentPositionLastIteration = false;
		} else {
			// Second priority: Is the stick outside the deadband?
			if (Math.abs(Robot.oi.getElevatorSpeed()) > 0) {
				SmartDashboard.putString(ELEV_MODE, "Set speed");
				Robot.elevator.setElevatorSpeed(Robot.oi.getElevatorSpeed());
				wasHoldingPresentPositionLastIteration = false;
			} else {
				// Third priority: hold the present position
				if (!wasHoldingPresentPositionLastIteration) {
					seekHoldPoint(ElevatorHoldPoint.HERE);
					// System.out.println("TEST THE ELEVATOR HOLD POINT IS: " +
					// ElevatorHoldPoint.HERE);
					wasHoldingPresentPositionLastIteration = true;
				} else {
					// We already commanded the elevator to hold its present
					// position, so we don't need to command it to do so again.
				}
				SmartDashboard.putString(ELEV_MODE, "Hold present position");
			}
		}
	}

	@Override
	public synchronized void cancel() {
		// Cancel any position seeking
		Robot.elevator.stopMotor();
	}

	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

	/**
	 * Seek a predefined point. The elevator will seek and hold this point until it
	 * loses power or receives a new command. It will remember to seek this point
	 * when disabled, unless a command is given to cause it to forget.
	 * 
	 * @param point
	 *            - which point to seek, see ElevatorHoldPoint
	 */
	private void seekHoldPoint(ElevatorHoldPoint point) {
		double desiredHeightFeet = 0;
		switch (point) {
		case FLOOR:
			desiredHeightFeet = FLOOR_HEIGHT_FEET;
			break;
		case SCALE_SCORE_HIGH:
			desiredHeightFeet = SCALE_SCORE_HIGH_HEIGHT_FEET;
			break;
		case SCALE_SCORE_MED:
			desiredHeightFeet = SCALE_SCORE_MED_HEIGHT_FEET;
			break;
		case SCALE_SCORE_LOW:
			desiredHeightFeet = SCALE_SCORE_LOW_HEIGHT_FEET;
			break;
		case SWITCH_SCORE:
			desiredHeightFeet = SWITCH_SCORE_HEIGHT_FEET;
			break;
		case HERE: // Fallthrough - the HERE and default cases have the same action
		default:
			desiredHeightFeet = Robot.elevator.getElevatorHeightFeet();
			break;
		}

		Robot.elevator.setElevatorHeight(desiredHeightFeet);
	}
}
