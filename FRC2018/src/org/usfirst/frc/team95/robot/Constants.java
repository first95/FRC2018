package org.usfirst.frc.team95.robot;

public class Constants
	{
	
		public static double joystickDeadbandV = 0.07;
		public static double joystickDeadbandH = 0.05;
		
		public static final double MAX_CLIMBER_SPEED = 0.8;

		// Properties of the robot design 
		public static final double ROBOT_WHEELBASE_WIDTH_INCHES = 23.0; // Distance between the centers of the wheels
		public static final double ROBOT_TOP_SPEED_LOW_GEAR_FPS = 7; 
		public static final double ROBOT_TOP_SPEED_HIGH_GEAR_FPS = 17; 
		
		// Used in closed-loop control
		public static final double ELEVATOR_ON_TARGET_THRESHOLD_INCHES = 1; // Elevator will call itself close enough at this point
		public static final double CLIMBER_ON_TARGET_THRESHOLD_INCHES = 1; // Climber will call itself close enough at this point
		public static final double DRIVEPOD_ON_TARGET_THRESHOLD_INCHES = 1; // Each drivepod will call itself close enough at this point		

		// Speed Shifter Values
		public static final double SPEED_TO_SHIFT_UP = 5.5; // ft per sec
		public static final double SPEED_TO_SHIFT_DOWN = 5.0; // ft per sec

		// Used with Talons
		public static final int PID_IDX = 0; // The Talons support up to 2 PID loops, with indexes 0 and 1.  We only use 0.
		public static final int CAN_TIMEOUT_MS = 10; // The amount of time to wait for the CAN transaction to finish
		public static final int CAN_ORDINAL_SLOT0 = 0;
		
		// Indices for solenoids
		public static final int SHIFTER_SOLENOID_NUM  = 0;
		public static final int COLLECTOR_SOLENOID_NUM  = 1;
		public static final int WRIST_STAGE_ONE = 3; // The longer piston
		public static final int WRIST_STAGE_TWO = 2; // The shorter piston
		
		// Indices for sensors
		public static final int ELEVATOR_HOME_SWITCH_DIO_NUM = 0;
		public static final int LEFT_COLLECTOR_PHOTOSENSOR_DIO_NUM = 3;
		public static final int MIDDLE_COLLECTOR_PHOTOSENSOR_DIO_NUM = 1;
		public static final int RIGHT_COLLECTOR_PHOTOSENSOR_DIO_NUM = 2;

		// Indices for Talons
		// Drive base
		public static final int LEFT_LEAD = 10;
		public static final int LEFT_F1 = 11;
		public static final int LEFT_F2 = 12;
		public static final int RIGHT_LEAD = 20;
		public static final int RIGHT_F1 = 21;
		public static final int RIGHT_F2 = 22;
		
		// Elevator
		public static final int LEFT_ELEV_DRIVER = 13;
		public static final int RIGHT_ELEV_DRIVER = 23;
		
		// Collector
		public static final int LEFT_CHAIN_DRIVER  = 14;
		public static final int RIGHT_CHAIN_DRIVER = 24;

		// Climber
		public static final int LEFT_CLIMBER_DRIVER = 25;
		public static final int RIGHT_CLIMBER_DRIVER = 15;
		
		// Sensors attached via Talon
		public static final int PIGEON_NUM = 30;		
		
		// Current limiting parameters
		public static final int DRIVEPOD_MAX_CURRENT_CONTINUAL_AMPS = 10;
		public static final int DRIVEPOD_MAX_CURRENT_PEAK_AMPS = 5;
		public static final int DRIVEPOD_MAX_CURRENT_PEAK_DURATION_MS = 100;		
		
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