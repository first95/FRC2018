package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team95.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	// Axes on drive stick
	public static int COLLECTOR_IN_AXIS  = 2;
	public static int COLLECTOR_OUT_AXIS = 3;
	
	// Buttons on drive stick
	public static int SHIFT_BUTTON = 2; // B
	public static int OPEN_COLLECTOR_BUTTON = 5; // left bumper

	
	private Joystick driverController  = new Joystick(0);
	private Joystick weaponsController = new Joystick(1);
	//private XboxController xbox = new XboxController(0);

	public OI() {
		// Put Some buttons on the SmartDashboard
		SmartDashboard.putData("Go to Switch", new GoToSwitch());
		
		// Create some buttons
		JoystickButton joy_A = new JoystickButton(driverController, 1);

		// Connect the buttons to commands
		joy_A.whenPressed(new Nothing());
		//if (xbox.getAButtonPressed()) {
		//	new Nothing();
		//}
		
		
		//a.whenPressed(new ShiftGear());
	}

	public Joystick getDrivestick() {
		return driverController;
	}
	
	// Collector controls
	public boolean getCollectorOpen() {
		return weaponsController.getRawButton(OPEN_COLLECTOR_BUTTON);
	}
	public double getCollectorSpeed() {
		return weaponsController.getRawAxis(COLLECTOR_IN_AXIS)
             - weaponsController.getRawAxis(COLLECTOR_OUT_AXIS);
	}
	
	// Drive base controls
	public boolean getHighGear() {
		return driverController.getRawButton(SHIFT_BUTTON);
	}
}
