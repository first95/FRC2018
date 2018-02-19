package org.usfirst.frc.team95.robot.subsystems;

import org.usfirst.frc.team95.robot.Constants;
import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.commands.collector.ManuallyControlCollector;
import org.usfirst.frc.team95.robot.components.AdjustedTalon;
import org.usfirst.frc.team95.robot.components.SolenoidI;
import org.usfirst.frc.team95.robot.components.SolenoidWrapper;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Collector extends Subsystem {
	// We can wire the Banner photoelectric sensors in a few ways.
	// Here we specify the Digital IO value that will be returned
	// by DigitalInput.get() when a reflective object is present.
	// (we're using Banner part number QS18VN6LV)
	private static final boolean DIO_VALUE_FOR_DETECTION = true;
	
	// Motor controllers for the intake/expel chains
	private IMotorControllerEnhanced leftChainDriver, rightChainDriver;
	
	// The solenoids for the cylinder that opens the maw, and operates the wrist
	private SolenoidI mawOpener, wristStageOne, wristStageTwo;
	
	// The optical rangefinders that report the presence of a reflective object
	private DigitalInput leftPhotosensor, middlePhotosensor, rightPhotosensor;
	private DigitalInput photosensors[]; // An array of all 3
	
	public Collector() {
		super();
		leftChainDriver  = new AdjustedTalon(Constants.LEFT_CHAIN_DRIVER);
		rightChainDriver = new AdjustedTalon(Constants.RIGHT_CHAIN_DRIVER);
		mawOpener = new SolenoidWrapper(Constants.COLLECTOR_SOLENOID_NUM);
		
		// False means it is extended
		wristStageOne = new SolenoidWrapper(Constants.WRIST_STAGE_ONE);
		wristStageTwo = new SolenoidWrapper(Constants.WRIST_STAGE_TWO);
		
		// False means a sufficiently reflective object is within range (hopefully the cube)
		leftPhotosensor   = new DigitalInput(Constants.LEFT_COLLECTOR_PHOTOSENSOR_DIO_NUM);
		middlePhotosensor = new DigitalInput(Constants.MIDDLE_COLLECTOR_PHOTOSENSOR_DIO_NUM);
		rightPhotosensor  = new DigitalInput(Constants.RIGHT_COLLECTOR_PHOTOSENSOR_DIO_NUM);
		// This is just a container for the existing objects
		photosensors = new DigitalInput[] {leftPhotosensor, middlePhotosensor, rightPhotosensor};
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ManuallyControlCollector());
	}
	
	public void log() {
		SmartDashboard.putBoolean("Maw Open?" , Robot.oi.getCollectorOpen());
		SmartDashboard.putBoolean("Wrist Stage One Extended?", Robot.oi.getWristStageOneRetracted());
		SmartDashboard.putBoolean("Wrist Stage Two Extended?", Robot.oi.getWristStageTwoRetracted());
		SmartDashboard.putBoolean("Left maw photosensor detects?", isLeftMawPhotosensorTripped());
		SmartDashboard.putBoolean("Middle maw photosensor detects?", isMiddleMawPhotosensorTripped());
		SmartDashboard.putBoolean("Right maw photosensor detects?", isRightMawPhotosensorTripped());
	}

	public void setMawOpen(boolean open) {
		mawOpener.set(open);
	}
	
	public void setWristStageOneRetracted(boolean retracted) {
		wristStageOne.set(retracted);
	}
	
	public void setWristStageTwoRetracted(boolean retracted) {
		wristStageTwo.set(retracted);
	}
	
	public boolean getWristStageOneRetracted() {
		return wristStageOne.get();
	}
	
	public boolean getWristStageTwoRetracted() {
		return wristStageTwo.get();
	}
	
	
	public void setIntakeSpeed(double outwardThrottle) {
		leftChainDriver.set(ControlMode.PercentOutput, -outwardThrottle);
		rightChainDriver.set(ControlMode.PercentOutput, outwardThrottle);
	}
	
	/**
	 * @return the number of maw photosensors that are reporting
	 * the presence of a reflective object within range.
	 */
	public int getNumberOfMawPhotosensorsTripped() {
		int num_rfs = 0;
		
		for (DigitalInput orf : photosensors) {
			if(orf.get() == DIO_VALUE_FOR_DETECTION) {
				num_rfs++;
			}
		}
		
		return num_rfs;
	}
	
	public boolean isLeftMawPhotosensorTripped() {
		return (leftPhotosensor.get() == DIO_VALUE_FOR_DETECTION);
	}
	public boolean isMiddleMawPhotosensorTripped() {
		return (middlePhotosensor.get() == DIO_VALUE_FOR_DETECTION);
	}
	public boolean isRightMawPhotosensorTripped() {
		return (rightPhotosensor.get() == DIO_VALUE_FOR_DETECTION);
	}
}
