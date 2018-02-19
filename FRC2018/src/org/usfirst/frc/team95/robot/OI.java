package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team95.robot.Robot.StartPosition;
import org.usfirst.frc.team95.robot.commands.Nothing;
import org.usfirst.frc.team95.robot.commands.compound.AutoPickUpCubeManualDrive;
import org.usfirst.frc.team95.robot.commands.compound.AutoPickUpCubeWithDrive;
import org.usfirst.frc.team95.robot.oi.MutableSendableChooser;

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
	public static final int MAW_AUTOGRAB_BUTTON = 6; // Right bumper
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
	
	SendableChooser<StartPosition> robotStartingPosition = new SendableChooser<>();
	MutableSendableChooser<Command> moveSwitchLScaleL = new MutableSendableChooser<>();
	MutableSendableChooser<Command> moveSwitchLScaleR = new MutableSendableChooser<>();
	MutableSendableChooser<Command> moveSwitchRScaleL = new MutableSendableChooser<>();
	MutableSendableChooser<Command> moveSwitchRScaleR = new MutableSendableChooser<>();
	StartPosition lastSelectedPosition = null; // The position that was selected last iteration

	public OI() {
		// Put Some buttons on the SmartDashboard
		SmartDashboard.putData("Automatic cube pickup (manual drive)", new AutoPickUpCubeManualDrive());
		SmartDashboard.putData("Automatic cube pickup (automatic drive)", new AutoPickUpCubeWithDrive());

		// Create some buttons
		JoystickButton joy_A = new JoystickButton(driverController, 1);
		JoystickButton autograbButton = new JoystickButton(weaponsController, MAW_AUTOGRAB_BUTTON);

		// Connect the buttons to commands
		autograbButton.whileHeld(new AutoPickUpCubeManualDrive());

		// if (xbox.getAButtonPressed()) {
		// new Nothing();
		// }

		// a.whenPressed(new ShiftGear());
		

		// For the operators to indicate on which side of the field they placed the robot
		robotStartingPosition.addObject("Left",      StartPosition.LEFT);
		robotStartingPosition.addObject("Mid left",  StartPosition.MID_LEFT);
		robotStartingPosition.addDefault("Center",   StartPosition.CENTER);
		robotStartingPosition.addObject("Mid right", StartPosition.MID_RIGHT);
		robotStartingPosition.addObject("Right",     StartPosition.RIGHT);
		SmartDashboard.putData("Starting position", robotStartingPosition);

	}
	
	// There are a few things the OI wants to revisit every time around
	public void visit() {
		updateWristSettings();
		updateSmartChoosers();
	}
	
	public StartPosition getRobotStartPosition() {
		return robotStartingPosition.getSelected();
	}

	// We've got some SendableChooserse that need updating based on the selected robot position
	public void updateSmartChoosers() {
		StartPosition curPos = robotStartingPosition.getSelected();
		
		if(curPos != lastSelectedPosition) {
			System.out.println("Updating auto move choices list");
			updateLLAutoMoveChooser(curPos);
			updateLRAutoMoveChooser(curPos);
			updateRLAutoMoveChooser(curPos);
			updateRRAutoMoveChooser(curPos);
		}
		
		lastSelectedPosition = curPos;
		
	}
	
	private void updateLLAutoMoveChooser(StartPosition robotStartPosition) {
		// Clear it out
		moveSwitchLScaleL.clear();
		
		// Default move is also the closest thing we have to a label
		moveSwitchLScaleL.addDefault("SW L, SC L: Nothing", new Nothing());
		
		switch(robotStartPosition) {
		case LEFT:
			moveSwitchLScaleL.addObject("LL L", new Nothing());
			break;
		case MID_LEFT:
			moveSwitchLScaleL.addObject("LL ML", new Nothing());
			break;
		case CENTER:
			moveSwitchLScaleL.addObject("LL C", new Nothing());
			break;
		case MID_RIGHT:
			moveSwitchLScaleL.addObject("LL MR", new Nothing());
			break;
		case RIGHT:
			moveSwitchLScaleL.addObject("LL R", new Nothing());
			break;
		default:
			break;
		}
	}
	
	private void updateLRAutoMoveChooser(StartPosition robotStartPosition) {
		// Clear it out
		moveSwitchLScaleR.clear();
		
		// Default move is also the closest thing we have to a label
		moveSwitchLScaleR.addDefault("SW L, SC R: Nothing", new Nothing());
		
		switch(robotStartPosition) {
		case LEFT:
			moveSwitchLScaleR.addObject("LR L", new Nothing());
			break;
		case MID_LEFT:
			moveSwitchLScaleR.addObject("LR ML", new Nothing());
			break;
		case CENTER:
			moveSwitchLScaleR.addObject("LR C", new Nothing());
			break;
		case MID_RIGHT:
			moveSwitchLScaleR.addObject("LR MR", new Nothing());
			break;
		case RIGHT:
			moveSwitchLScaleR.addObject("LR R", new Nothing());
			break;
		default:
			break;
		}
	}
	
	private void updateRLAutoMoveChooser(StartPosition robotStartPosition) {
		// Clear it out
		moveSwitchRScaleL.clear();
		
		// Default move is also the closest thing we have to a label
		moveSwitchRScaleL.addDefault("SW R, SC L: Nothing", new Nothing());
		
		switch(robotStartPosition) {
		case LEFT:
			moveSwitchRScaleL.addObject("RL L", new Nothing());
			break;
		case MID_LEFT:
			moveSwitchRScaleL.addObject("RL ML", new Nothing());
			break;
		case CENTER:
			moveSwitchRScaleL.addObject("RL C", new Nothing());
			break;
		case MID_RIGHT:
			moveSwitchRScaleL.addObject("RL MR", new Nothing());
			break;
		case RIGHT:
			moveSwitchRScaleL.addObject("RL R", new Nothing());
			break;
		default:
			break;
		}
	}
	
	private void updateRRAutoMoveChooser(StartPosition robotStartPosition) {
		// Clear it out
		moveSwitchRScaleR.clear();
		
		// Default move is also the closest thing we have to a label
		moveSwitchRScaleR.addDefault("SW R, SC R: Nothing", new Nothing());
		
		switch(robotStartPosition) {
		case LEFT:
			moveSwitchRScaleR.addObject("RR L", new Nothing());
			break;
		case MID_LEFT:
			moveSwitchRScaleR.addObject("RR ML", new Nothing());
			break;
		case CENTER:
			moveSwitchRScaleR.addObject("RR C", new Nothing());
			break;
		case MID_RIGHT:
			moveSwitchRScaleR.addObject("RR MR", new Nothing());
			break;
		case RIGHT:
			moveSwitchRScaleR.addObject("RR R", new Nothing());
			break;
		default:
			break;
		}
	}
	
	public Command getSelectedCommand(FieldSide switchPosOurColor, FieldSide scalePosOurColor) {
		if(switchPosOurColor == FieldSide.LEFT && scalePosOurColor == FieldSide.LEFT) {
			return moveSwitchLScaleL.getSelected();
		} else if(switchPosOurColor == FieldSide.LEFT && scalePosOurColor == FieldSide.RIGHT) {
			return moveSwitchLScaleR.getSelected();
		} else if(switchPosOurColor == FieldSide.RIGHT && scalePosOurColor == FieldSide.LEFT) {
			return moveSwitchRScaleL.getSelected();
		} else if(switchPosOurColor == FieldSide.RIGHT && scalePosOurColor == FieldSide.RIGHT) {
			return moveSwitchRScaleR.getSelected();
		} else {
			return new Nothing();
		}
	}
	
	public void log() {
//		SmartDashboard.putNumber("Weapons stick POV", weaponsController.getPOV());
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
	// Full up    extended  extended  Up
	// some up    extended  retracted Up/right, left/up
	// some down  retracted extended  Right, left
	// full down  retracted retracted Down , right/down, left/down
	public void updateWristSettings() {
		if(weaponsController.getPOV() != POV_NONE) {
			// Per table above, retract stage one if the POV hat is right or down
			stageOneRetracted = (weaponsController.getPOV() >= POV_RIGHT && weaponsController.getPOV() <= POV_DOWN_LEFT);
			// Retract if the POV hat is up or down-ish
			stageTwoRetracted = (weaponsController.getPOV() >= POV_RIGHT_DOWN && weaponsController.getPOV() <= POV_LEFT);
		} else {
			// When no D-Pad button is pressed, don't change the angle
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
