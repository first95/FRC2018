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
	public static final int POV_NONE       = -1;  // No DPAD button pressed
	public static final int POV_UP         = 0;  
	public static final int POV_UP_RIGHT   = 45; 
	public static final int POV_RIGHT      = 90; 
	public static final int POV_RIGHT_DOWN = 135;
	public static final int POV_DOWN       = 180;
	public static final int POV_DOWN_LEFT  = 225;
	public static final int POV_LEFT       = 270;
	public static final int POV_LEFT_UP    = 315;
	
    private boolean stageOneRetracted = false;
    private boolean stageTwoRetracted = false;

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
	// We support 4 positions:
	//            Stage 1   Stage 2   POV position
	// Full up    extended  extended  released or any other
	// some up    extended  retracted Up, up/right, left/up
	// some down  retracted extended  Right, left
	// full down  retracted retracted Down , right/down, left/down
	public void updateWristSettings() {
		if(weaponsController.getPOV() != POV_NONE) {
			// Per table above, retract stage one if the POV hat is right or down
			stageOneRetracted = (weaponsController.getPOV() >= POV_UP_RIGHT && weaponsController.getPOV() <= POV_LEFT_UP);
			// Retract if the POV hat is up or down-ish
			stageTwoRetracted = (weaponsController.getPOV() == POV_UP || (weaponsController.getPOV() >= POV_RIGHT_DOWN && weaponsController.getPOV() <= POV_DOWN_LEFT));
		}
	}
	public boolean getWristStageOneRetracted() {
		return stageOneRetracted;
	}

	public boolean getWristStageTwoRetracted() {
		return stageTwoRetracted;
	}

	// Elevator controls
	public double getElevatorSpeed() {

		double elevatorSpeed = 0;

		if ((weaponsController.getRawAxis(ELEVATOR_AXIS) > .18)
				|| (weaponsController.getRawAxis(ELEVATOR_AXIS) < -.18)) {
			elevatorSpeed = weaponsController.getRawAxis(ELEVATOR_AXIS);
		}

		// The Y axis is reversed, so that positive is down
		return -elevatorSpeed;
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
