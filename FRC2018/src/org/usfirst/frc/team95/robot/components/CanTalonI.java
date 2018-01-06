/*
 * CanTalonI
 * 
 * This interface requires the presence of every method exposed by the CTRE CANTalon class.
 * It only exists to make unit testing easier.
 * Automatically generated:
 * 1. Use Eclipse to view CANTalon.class
 * 2. Paste that into a file (eg temp.txt)
 * 3. Grep it for the regex /public [a-zA-Z0-9]+ [a-zA-Z0-9]+\(/
 * 		(eg grep -E 'public [a-zA-Z0-9]+ [a-zA-Z0-9]+\(' temp.txt > temp.java)
 * 4. Paste that output into the interface body.
 * 	
 */

package org.usfirst.frc.team95.robot.components;

public interface CanTalonI {
	public void pidWrite(double output);
	public void setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType pidSource);
	public double pidGet();
	public void delete();
	public void set(double outputValue);
	public void setInverted(boolean isInverted);
	public boolean getInverted();
	public void reset();
	public boolean isEnabled();
	public double getError();
	public void setSetpoint(double setpoint);
	public void reverseSensor(boolean flip);
	public void reverseOutput(boolean flip);
	public double get();
	public int getEncPosition();
	public void setEncPosition(int newPosition);
	public int getEncVelocity();
	public int getPulseWidthPosition();
	public void setPulseWidthPosition(int newPosition);
	public int getPulseWidthVelocity();
	public int getPulseWidthRiseToFallUs();
	public int getPulseWidthRiseToRiseUs();
	public int getNumberOfQuadIdxRises();
	public int getPinStateQuadA();
	public int getPinStateQuadB();
	public int getPinStateQuadIdx();
	public void setAnalogPosition(int newPosition);
	public int getAnalogInPosition();
	public int getAnalogInRaw();
	public int getAnalogInVelocity();
	public int getClosedLoopError();
	public void setAllowableClosedLoopErr(int allowableCloseLoopError);
	public boolean isFwdLimitSwitchClosed();
	public boolean isRevLimitSwitchClosed();
	public boolean isZeroSensorPosOnIndexEnabled();
	public boolean isZeroSensorPosOnRevLimitEnabled();
	public boolean isZeroSensorPosOnFwdLimitEnabled();
	public boolean getBrakeEnableDuringNeutral();
	public void configEncoderCodesPerRev(int codesPerRev);
	public void configPotentiometerTurns(int turns);
	public double getTemperature();
	public double getOutputCurrent();
	public double getOutputVoltage();
	public double getBusVoltage();
	public double getPosition();
	public void setPosition(double pos);
	public double getSpeed();
	public void setControlMode(int mode);
	public void changeControlMode(com.ctre.CANTalon.TalonControlMode controlMode);
	public void setFeedbackDevice(com.ctre.CANTalon.FeedbackDevice device);
	public void setStatusFrameRateMs(com.ctre.CANTalon.StatusFrameRate stateFrame, int periodMs);
	public void enableControl();
	public void enable();
	public void disableControl();
	public boolean isControlEnabled();
	public double getP();
	public double getI();
	public double getD();
	public double getF();
	public double getIZone();
	public double getCloseLoopRampRate();
	public long GetFirmwareVersion();
	public long GetIaccum();
	public void setP(double p);
	public void setI(double i);
	public void setD(double d);
	public void setF(double f);
	public void setIZone(int izone);
	public void setCloseLoopRampRate(double rampRate);
	public void setVoltageRampRate(double rampRate);
	public void setVoltageCompensationRampRate(double rampRate);
	public void ClearIaccum();
	public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile);
	public void setPID(double p, double i, double d);
	public double getSetpoint();
	public void setProfile(int profile);
//	public void stopMotor();
	public void disable();
	public int getDeviceID();
	public void clearIAccum();
	public void setForwardSoftLimit(double forwardLimit);
	public int getForwardSoftLimit();
	public void enableForwardSoftLimit(boolean enable);
	public boolean isForwardSoftLimitEnabled();
	public void setReverseSoftLimit(double reverseLimit);
	public int getReverseSoftLimit();
	public void enableReverseSoftLimit(boolean enable);
	public boolean isReverseSoftLimitEnabled();
	public void configMaxOutputVoltage(double voltage);
	public void configPeakOutputVoltage(double forwardVoltage, double reverseVoltage);
	public void configNominalOutputVoltage(double forwardVoltage, double reverseVoltage);
	public void setParameter(com.ctre.CanTalonJNI.param_t paramEnum, double value);
	public double getParameter(com.ctre.CanTalonJNI.param_t paramEnum);
	public void clearStickyFaults();
	public void enableLimitSwitch(boolean forward, boolean reverse);
	public void ConfigFwdLimitSwitchNormallyOpen(boolean normallyOpen);
	public void ConfigRevLimitSwitchNormallyOpen(boolean normallyOpen);
	public void enableBrakeMode(boolean brake);
	public int getFaultOverTemp();
	public int getFaultUnderVoltage();
	public int getFaultForLim();
	public int getFaultRevLim();
	public int getFaultHardwareFailure();
	public int getFaultForSoftLim();
	public int getFaultRevSoftLim();
	public int getStickyFaultOverTemp();
	public int getStickyFaultUnderVoltage();
	public int getStickyFaultForLim();
	public int getStickyFaultRevLim();
	public int getStickyFaultForSoftLim();
	public int getStickyFaultRevSoftLim();
	public void enableZeroSensorPositionOnIndex(boolean enable, boolean risingEdge);
	public void enableZeroSensorPositionOnForwardLimit(boolean enable);
	public void enableZeroSensorPositionOnReverseLimit(boolean enable);
	public void changeMotionControlFramePeriod(int periodMs);
	public void clearMotionProfileTrajectories();
	public int getMotionProfileTopLevelBufferCount();
	public boolean pushMotionProfileTrajectory(com.ctre.CANTalon.TrajectoryPoint trajPt);
	public boolean isMotionProfileTopLevelBufferFull();
	public void processMotionProfileBuffer();
	public void getMotionProfileStatus(com.ctre.CANTalon.MotionProfileStatus motionProfileStatus);
	public void clearMotionProfileHasUnderrun();
	public void setMotionMagicCruiseVelocity(double motMagicCruiseVeloc);
	public void setMotionMagicAcceleration(double motMagicAccel);
	public double getMotionMagicCruiseVelocity();
	public double getMotionMagicAcceleration();
	public void setCurrentLimit(int amps);
	public void EnableCurrentLimit(boolean enable);
	public int GetGadgeteerStatus(com.ctre.GadgeteerUartClient.GadgeteerUartStatus status);
	public void setExpiration(double timeout);
	public double getExpiration();
	public boolean isAlive();
	public boolean isSafetyEnabled();
	public void setSafetyEnabled(boolean enabled);
	public void initTable(edu.wpi.first.wpilibj.tables.ITable subtable);
	public void updateTable();
	public void startLiveWindowMode();
	public void stopLiveWindowMode();

}
