package org.usfirst.frc.team95.robot.components;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AdjustedTalon extends CanTalonIWrapper implements CanTalonI
	{
		private static PowerDistributionPanelI panel;
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

		public AdjustedTalon(CanTalonI device, PowerDistributionPanelI pdp) {
			super(device);
			panel = pdp;
		}
		
		@Override
		public void set(double rate)
			{
				// TODO: Right now we're checking voltage once per AdjustedTalon, when we technically only need to do it once per iteration for the whole robot
				double current = super.getOutputCurrent();
				double voltage = panel.getVoltage();
				double newAtten;
				
				voltageRec.add(voltage);
				if (voltageRec.size() > NUM_RECENT_SAMPLES) {
					voltageRec.remove();
				}
				
				voltage = Collections.min(voltageRec);
				
				if (rate < 0.0)
					{
						rate *= BACKWARDS_MULTIPLIER;

						rate = Math.min(rate, 1);
						rate = Math.max(rate, -1);
					}
				
				if (voltage < MIN_VOLTAGE) {
					voltage = MIN_VOLTAGE;
				}
				if (voltage < MAX_VOLTAGE)
					{
						newAtten = (SLOPEV * voltage) + INTERCEPTV;
						newAtten = Math.max(newAtten, MIN_ATTENV);
						rate *= newAtten;
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
				super.set(rate);
			}

	}