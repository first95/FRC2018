/*
 * PigeonWrapper.java
 * 
 * This class is a simple container for the CTRE PigeonIMU class.
 * 
 * Using this class, rather than using the PigeonIMU class itself
 * limits us to accessing and using only those methods that we
 * understand.
 * 
 */
package org.usfirst.frc.team95.robot.components;

import com.ctre.phoenix.sensors.PigeonIMU;

import org.usfirst.frc.team95.robot.Constants;

public class PigeonWrapper {
	private PigeonIMU wrapped;
	private PigeonIMU.GeneralStatus genStatus;
	
	public PigeonWrapper(final int can_num) {
		wrapped = new PigeonIMU(can_num);
	}
	
	public void getGeneralStatus() {
		genStatus = new PigeonIMU.GeneralStatus();
		wrapped.getGeneralStatus(genStatus);
	}
	
	public double [] getYawPitchRoll() {
		double [] ypr = new double[3];
		wrapped.getYawPitchRoll(ypr);
		//System.out.println("Yaw:" + ypr[0]);
		return ypr;
	}
	
	/**
	 * TODO: figure out the difference between yaw, compass heading, and fused heading
	 * @return
	 */
	public double getFusedHeading() {
		return wrapped.getFusedHeading();
	}

	public void setYaw(final double newAngle) {
		wrapped.setYaw(newAngle, Constants.CAN_TIMEOUT_MS);
	}

}
