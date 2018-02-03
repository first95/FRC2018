package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team95.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	// Axes on weapons controller
	public static final int COLLECTOR_IN_AXIS = 2;
	public static final int COLLECTOR_OUT_AXIS = 3;
	public static final int ELEVATOR_AXIS = 5; // Right stick Y

	// Buttons on drive controller
	public static final int SHIFT_BUTTON = 5; // Left bumper

	// Buttons on weapons controller
	public static final int OPEN_COLLECTOR_BUTTON = 5; // Left bumper
	public static final int ELEV_SEEK_FLOOR_BUTTON = 1; // A
	public static final int ELEV_SEEK_SWITCH_SCORE_BUTTON = 2; // B
	public static final int ELEV_SEEK_SCALE_SCORE_LOW_BUTTON = 3; // X
	public static final int ELEV_SEEK_SCALE_SCORE_HIGH_BUTTON = 4; // Y

	// POV/DPAD on the weapons controller || IT IS IN DEGREES!!!!!
	public static final int EXTEND_WRIST_STAGE_ONE_POV = 0; // DPAD UP
	public static final int EXTEND_WRIST_STAGE_TWO_POV = 90; // DPAD RIGHT
	public static final int RESET_WRIST = 180; // DPAD DOWN
	private boolean stageOneExtended = false;
	private boolean stageTwoExtended = false;
	private boolean stageOneWasNotPressed = true;
	private boolean stageTwoWasNotPressed = true;

	private Joystick driverController = new Joystick(0);
	private Joystick weaponsController = new Joystick(1);
//	 private XboxController xbox = new XboxController(0);

	public OI() {
		// Put Some buttons on the SmartDashboard
		SmartDashboard.putData("Go to Switch", new GoToSwitch());

		// Create some buttons
		JoystickButton joy_A = new JoystickButton(driverController, 1);

		// Connect the buttons to commands
		joy_A.whenPressed(new Nothing());
		// if (xbox.getAButtonPressed()) {
		// new Nothing();
		// }

		// a.whenPressed(new ShiftGear());
	}

	public void log() {
		SmartDashboard.putNumber("Weapons stick POV", weaponsController.getPOV());
	}
	
	// Collector controls
	public boolean getCollectorOpen() {
		return weaponsController.getRawButton(OPEN_COLLECTOR_BUTTON);
	}

	public double getCollectorSpeed() {
		return weaponsController.getRawAxis(COLLECTOR_IN_AXIS) - weaponsController.getRawAxis(COLLECTOR_OUT_AXIS);
	}

	// Wrist controls
	public boolean getWristStageOneExtended() {

		if (weaponsController.getPOV() == EXTEND_WRIST_STAGE_ONE_POV) {
			if (stageOneWasNotPressed) {
				if (stageOneExtended == true) {
					stageOneExtended = false;
				} else {
					stageOneExtended = true;
				}
				stageOneWasNotPressed = false;
			}
			
		}
		else {
			stageOneWasNotPressed = true;
		}
		return stageOneExtended;
	}

	public boolean getWristStageTwoExtended() {

		if (weaponsController.getPOV() == EXTEND_WRIST_STAGE_TWO_POV) {
			if (stageTwoWasNotPressed) {
				if (stageTwoExtended == true) {
					stageTwoExtended = false;
				} else {
					stageTwoExtended = true;
				}
				stageTwoWasNotPressed = false;
			}
			
		}
		else {
			stageTwoWasNotPressed = true;
		}
		
		return stageTwoExtended;
	}

	public void getWristReset() {

		if (weaponsController.getPOV() == RESET_WRIST) {
			Robot.collector.resetWrists();
		}

	}

	// Elevator controls
	public double getElevatorSpeed() {

		double elevatorSpeed = 0;

		if ((weaponsController.getRawAxis(ELEVATOR_AXIS) > .18)
				|| (weaponsController.getRawAxis(ELEVATOR_AXIS) < -.18)) {
			elevatorSpeed = weaponsController.getRawAxis(ELEVATOR_AXIS);
		}

		return elevatorSpeed;
	}
	public boolean isElevatorFloorButtonPressed() {
		return weaponsController.getRawButton(ELEV_SEEK_FLOOR_BUTTON);
	}
	public boolean isElevatorSwitchScoreButtonPressed() {
		return weaponsController.getRawButton(ELEV_SEEK_SWITCH_SCORE_BUTTON);
	}
	public boolean isElevatorScaleScoreLowButtonPressed() {
		return weaponsController.getRawButton(ELEV_SEEK_SCALE_SCORE_LOW_BUTTON);
	}
	public boolean isElevatorScaleScoreHighButtonPressed() {
		return weaponsController.getRawButton(ELEV_SEEK_SCALE_SCORE_HIGH_BUTTON);
	}
	

	// Drive base controls
	public double getForwardAxis() {
		return driverController.getY();
	}

	public double getTurnAxis() {
		return driverController.getRawAxis(4);
	}

	public boolean getHighGear() {
		return driverController.getRawButton(SHIFT_BUTTON);
	}
}
