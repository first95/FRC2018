package org.usfirst.frc.team95.robot;

public class Constants
	{
	
		public static double joystickDeadbandV = 0.07;
		public static double joystickDeadbandH = 0.05;

		// Used with Talons
		public static int PID_IDX = 0; // The Talons support up to 2 PID loops, with indexes 0 and 1.  We only use 0.
		public static int CAN_TIMEOUT_MS = 10; // The amount of time to wait for the CAN transaction to finish
		
		// Indices for solenoids
		public static int SHIFTER_SOLENOID_NUM  = 0;
		public static int COLLECTOR_SOLENOID_NUM  = 1;
		public static int WRIST_STAGE_ONE = 2;
		public static int WRIST_STAGE_TWO = 3;

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
		
		// Constants
		// Encoder values recycled from last year
		public final static double ENCODER_TICKS_PER_FOOT = 1002;
		public final static double ENCODER_TICKS_PER_RADIAN = 1637.39265;//1100

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