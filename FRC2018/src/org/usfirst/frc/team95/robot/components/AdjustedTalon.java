package org.usfirst.frc.team95.robot.components;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

public class AdjustedTalon extends TalonSrxWrapper
	{
		private PowerDistributionPanelI panel;
		public static final int NUM_RECENT_SAMPLES = 3;
		public static final double BACKWARDS_MULTIPLIER = 1.0 / 0.92; // Main CIMs run about 8% less efficiently going backwards. Reverse that.
		public static final double MIN_CURRENT = 40.0;
		public static final double MAX_CURRENT = 90.0;
		public static final double MIN_ATTENC = 0.95;
		public static final double MAX_ATTENC = 0.05;
		public static final double SLOPEC = ((MAX_ATTENC - MIN_ATTENC) / (MAX_CURRENT - MIN_CURRENT));
		public static final double INTERCEPTC = (MIN_ATTENC - (SLOPEC * MIN_CURRENT));
		public static final double MIN_VOLTAGE = 7.5;
		public static final double MAX_VOLTAGE = 9.5;
		public static final double MIN_ATTENV = 0.05;
		public static final double MAX_ATTENV = 0.95;
		public static final double SLOPEV = ((MAX_ATTENV - MIN_ATTENV) / (MAX_VOLTAGE - MIN_VOLTAGE));
		public static final double INTERCEPTV = (MIN_ATTENV - (SLOPEV * MIN_VOLTAGE));
		Queue<Double> voltageRec = new LinkedList<Double>();

		public AdjustedTalon(int deviceNumber) {
			super(deviceNumber);
			panel = new PowerDistributionPanelWrapper();
		}
		public AdjustedTalon(IMotorControllerEnhanced wrapped, PowerDistributionPanelI panel) {
			super(wrapped);
			this.panel = panel;
		}

		@Override
		public void set(ControlMode mode,  double value)
			{
				if (mode == ControlMode.PercentOutput) {
					// In this mode, the value is a rate
					// TODO: Right now we're checking voltage once per AdjustedTalon, when we technically only need to do it once per iteration for the whole robot
					double current = super.getOutputCurrent();
					double voltage = panel.getVoltage();
					double newAtten;
					
					voltageRec.add(voltage);
					if (voltageRec.size() > NUM_RECENT_SAMPLES) {
						voltageRec.remove();
					}
					
					voltage = Collections.min(voltageRec);
					
					if (value < 0.0)
						{
						value *= BACKWARDS_MULTIPLIER;
	
							value = Math.min(value, 1);
							value = Math.max(value, -1);
						}
					
					if (voltage < MIN_VOLTAGE) {
						voltage = MIN_VOLTAGE;
					}
					if (voltage < MAX_VOLTAGE)
						{
							newAtten = (SLOPEV * voltage) + INTERCEPTV;
							newAtten = Math.max(newAtten, MIN_ATTENV);
							value *= newAtten;
							// System.out.println("Atenn" + newAtten);
						}else {
							newAtten = 1;
						}
	//				SmartDashboard.putNumber("Atten", newAtten);
					/*else if (current < MAX_CURRENT)
						{
							newAtten = (SLOPEC * current) + INTERCEPTC;
							newAtten = Math.max(newAtten, MAX_ATTENC);
							rate *= newAtten;
							// System.out.println("Atenn" + newAtten);
						}*/
					// System.out.println("Voltage" + panel.getVoltage());
					// System.out.println("Rate" + rate);
				}
				
				super.set(mode, value);
			}

	}