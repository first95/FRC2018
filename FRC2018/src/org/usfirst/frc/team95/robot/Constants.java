package org.usfirst.frc.team95.robot;

public class Constants
	{
		public static double joystickDeadbandV = 0.07;
		public static double joystickDeadbandH = 0.05;

		// Indices for solenoids
		public static int SHIFTER_SOLENOID_NUM  = 0;
		public static int COLLECTOR_SOLENOID_NUM  = 1;

		// Indices for Talons
		// Drive base
		public static int LEFT_LEAD = 10;
		public static int LEFT_F1 = 11;
		public static int LEFT_F2 = 12;
		public static int RIGHT_LEAD = 20;
		public static int RIGHT_F1 = 21;
		public static int RIGHT_F2 = 22;
		// Elevator
		public static int LEFT_ELEV_DRIVER = 13;
		public static int RIGHT_ELEV_DRIVER = 23;
		// Collector
		public static int LEFT_CHAIN_DRIVER  = 14;
		public static int RIGHT_CHAIN_DRIVER = 24;

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