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
	private Joystick joy = new Joystick(0);
	//private XboxController xbox = new XboxController(0);

	public OI() {
		// Put Some buttons on the SmartDashboard
		SmartDashboard.putData("Go to Switch", new GoToSwitch());
		
		// Create some buttons
		JoystickButton joy_A = new JoystickButton(joy, 1);

		// Connect the buttons to commands
		joy_A.whenPressed(new Nothing());
		//if (xbox.getAButtonPressed()) {
		//	new Nothing();
		//}
		
		
		//a.whenPressed(new ShiftGear());
	}

	public Joystick getJoystick() {
		return joy;
	}
}
