package org.usfirst.frc.team95.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Constants
	{
		public static double joystickDeadbandV = 0.07;
		public static double joystickDeadbandH = 0.05;
		public static Joystick driveStick = new Joystick(0);
		public static Joystick weaponStick = new Joystick(1);
		public static double poseidonNorthVal = 2.751;
		public static double encoderTickPerFoot = 1002;
		public static double encTicksPerRadian = 1110;
		public static double robotWidth = 34.7;
		public static double visionLength = 3;
		public static double FLOOR_INTAKE_THROTTLE = 0.7;
		public static int CLIMBER_AXIS = 1;
		
		public static int LEFT_SHIFTER_SOLENOID_NUM  = 0;
		public static int RIGHT_SHIFTER_SOLENOID_NUM = 1;
		public static int SHIFT_BUTTON = 1; // TODO: Pick a good button
		
		public static int TEST_ARM_AXIS = 2; 
		public static int EXAMPLE_MOTOR_AXIS = 6; // there isn't actually an axis #6; this is just an example

		public static double RFVoltsToFt(double voltage)
			{
				double distance;
				// Sonar Range finder based on data sheet almost acurrate
				// distance = (voltage * 100) / .977; //mV to mm

				// sonar based on experimentation (in cm)
				distance = (voltage * 107.96) - 3.0219;

				// convert from cm to ft
				distance = distance * 0.0328084;
				return distance;
			}
	}