/*
 * CanTalonWrapper
 * 
 * This class wraps the CANTalon class, but also implements the CanTalonI
 * interface. 
 * Semi-automatically generated:
 * 1. Open CanTalonI
 * 2. Perform Notepad++ regex find & replace: 
 *   Find: / ([a-zA-Z0-9]+)\(\)\;/
 *   Replace with: / \1\(\) { return wrapped.\1\(\); }/
 * 3. Perform Notepad++ regex find & replace: 
 *   Find: / ([a-zA-Z0-9]+)\((.+)\)\;$/
 *   Replace with: / \1\(\2\) { return wrapped.\1\(\); }/
 * 4. Paste here, and manually fix the rest

 */
package org.usfirst.frc.team95.robot.components;

public class CanTalonIWrapper implements CanTalonI {
	protected CanTalonI wrapped; // The inner talon

	public CanTalonIWrapper(int deviceNumber) {
		wrapped = new CanTalonWrapper(deviceNumber);
	}

	public CanTalonIWrapper(CanTalonI device) {
		wrapped = device;
	}

	public void pidWrite(double output) { wrapped.pidWrite(output); }
	public void setPIDSourceType(edu.wpi.first.wpilibj.PIDSourceType pidSource) { wrapped.setPIDSourceType(pidSource); }
	public double pidGet() { return wrapped.pidGet(); }
	public void delete() { wrapped.delete(); }
	public void set(double outputValue) { wrapped.set(outputValue); }
	public void setInverted(boolean isInverted) { wrapped.setInverted(isInverted); }
	public boolean getInverted() { return wrapped.getInverted(); }
	public void reset() { wrapped.reset(); }
	public boolean isEnabled() { return wrapped.isEnabled(); }
	public double getError() { return wrapped.getError(); }
	public void setSetpoint(double setpoint) { wrapped.setSetpoint(setpoint); }
	public void reverseSensor(boolean flip) { wrapped.reverseSensor(flip); }
	public void reverseOutput(boolean flip) { wrapped.reverseOutput(flip); }
	public double get() { return wrapped.get(); }
	public int getEncPosition() { return wrapped.getEncPosition(); }
	public void setEncPosition(int newPosition) { wrapped.setEncPosition(newPosition); }
	public int getEncVelocity() { return wrapped.getEncVelocity(); }
	public int getPulseWidthPosition() { return wrapped.getPulseWidthPosition(); }
	public void setPulseWidthPosition(int newPosition) { wrapped.setPulseWidthPosition(newPosition); }
	public int getPulseWidthVelocity() { return wrapped.getPulseWidthVelocity(); }
	public int getPulseWidthRiseToFallUs() { return wrapped.getPulseWidthRiseToFallUs(); }
	public int getPulseWidthRiseToRiseUs() { return wrapped.getPulseWidthRiseToRiseUs(); }
	public int getNumberOfQuadIdxRises() { return wrapped.getNumberOfQuadIdxRises(); }
	public int getPinStateQuadA() { return wrapped.getPinStateQuadA(); }
	public int getPinStateQuadB() { return wrapped.getPinStateQuadB(); }
	public int getPinStateQuadIdx() { return wrapped.getPinStateQuadIdx(); }
	public void setAnalogPosition(int newPosition) { wrapped.setAnalogPosition(newPosition); }
	public int getAnalogInPosition() { return wrapped.getAnalogInPosition(); }
	public int getAnalogInRaw() { return wrapped.getAnalogInRaw(); }
	public int getAnalogInVelocity() { return wrapped.getAnalogInVelocity(); }
	public int getClosedLoopError() { return wrapped.getClosedLoopError(); }
	public void setAllowableClosedLoopErr(int allowableCloseLoopError) { wrapped.setAllowableClosedLoopErr(allowableCloseLoopError); }
	public boolean isFwdLimitSwitchClosed() { return wrapped.isFwdLimitSwitchClosed(); }
	public boolean isRevLimitSwitchClosed() { return wrapped.isRevLimitSwitchClosed(); }
	public boolean isZeroSensorPosOnIndexEnabled() { return wrapped.isZeroSensorPosOnIndexEnabled(); }
	public boolean isZeroSensorPosOnRevLimitEnabled() { return wrapped.isZeroSensorPosOnRevLimitEnabled(); }
	public boolean isZeroSensorPosOnFwdLimitEnabled() { return wrapped.isZeroSensorPosOnFwdLimitEnabled(); }
	public boolean getBrakeEnableDuringNeutral() { return wrapped.getBrakeEnableDuringNeutral(); }
	public void configEncoderCodesPerRev(int codesPerRev) { wrapped.configEncoderCodesPerRev(codesPerRev); }
	public void configPotentiometerTurns(int turns) { wrapped.configPotentiometerTurns(turns); }
	public double getTemperature() { return wrapped.getTemperature(); }
	public double getOutputCurrent() { return wrapped.getOutputCurrent(); }
	public double getOutputVoltage() { return wrapped.getOutputVoltage(); }
	public double getBusVoltage() { return wrapped.getBusVoltage(); }
	public double getPosition() { return wrapped.getPosition(); }
	public void setPosition(double pos) { wrapped.setPosition(pos); }
	public double getSpeed() { return wrapped.getSpeed(); }
	public void setControlMode(int mode) { wrapped.setControlMode(mode); }
	public void changeControlMode(com.ctre.CANTalon.TalonControlMode controlMode) { wrapped.changeControlMode(controlMode); }
	public void setFeedbackDevice(com.ctre.CANTalon.FeedbackDevice device) { wrapped.setFeedbackDevice(device); }
	public void setStatusFrameRateMs(com.ctre.CANTalon.StatusFrameRate stateFrame, int periodMs) { wrapped.setStatusFrameRateMs(stateFrame, periodMs); }
	public void enableControl() { wrapped.enableControl(); }
	public void enable() { wrapped.enable(); }
	public void disableControl() { wrapped.disableControl(); }
	public boolean isControlEnabled() { return wrapped.isControlEnabled(); }
	public double getP() { return wrapped.getP(); }
	public double getI() { return wrapped.getI(); }
	public double getD() { return wrapped.getD(); }
	public double getF() { return wrapped.getF(); }
	public double getIZone() { return wrapped.getIZone(); }
	public double getCloseLoopRampRate() { return wrapped.getCloseLoopRampRate(); }
	public long GetFirmwareVersion() { return wrapped.GetFirmwareVersion(); }
	public long GetIaccum() { return wrapped.GetIaccum(); }
	public void setP(double p) { wrapped.setP(p); }
	public void setI(double i) { wrapped.setI(i); }
	public void setD(double d) { wrapped.setD(d); }
	public void setF(double f) { wrapped.setF(f); }
	public void setIZone(int izone) { wrapped.setIZone(izone); }
	public void setCloseLoopRampRate(double rampRate) { wrapped.setCloseLoopRampRate(rampRate); }
	public void setVoltageRampRate(double rampRate) { wrapped.setVoltageRampRate(rampRate); }
	public void setVoltageCompensationRampRate(double rampRate) { wrapped.setVoltageCompensationRampRate(rampRate); }
	public void ClearIaccum() { wrapped.ClearIaccum(); }
	public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile) { wrapped.setPID(p, i, d, f, izone, closeLoopRampRate, profile); }
	public void setPID(double p, double i, double d) { wrapped.setPID(p, i, d); }
	public double getSetpoint() { return wrapped.getSetpoint(); }
	public void setProfile(int profile) { wrapped.setProfile(profile); }
//	public void stopMotor() { wrapped.stopMotor(); }
	public void disable() { wrapped.disable(); }
	public int getDeviceID() { return wrapped.getDeviceID(); }
	public void clearIAccum() { wrapped.clearIAccum(); }
	public void setForwardSoftLimit(double forwardLimit) { wrapped.setForwardSoftLimit(forwardLimit); }
	public int getForwardSoftLimit() { return wrapped.getForwardSoftLimit(); }
	public void enableForwardSoftLimit(boolean enable) { wrapped.enableForwardSoftLimit(enable); }
	public boolean isForwardSoftLimitEnabled() { return wrapped.isForwardSoftLimitEnabled(); }
	public void setReverseSoftLimit(double reverseLimit) { wrapped.setReverseSoftLimit(reverseLimit); }
	public int getReverseSoftLimit() { return wrapped.getReverseSoftLimit(); }
	public void enableReverseSoftLimit(boolean enable) { wrapped.enableReverseSoftLimit(enable); }
	public boolean isReverseSoftLimitEnabled() { return wrapped.isReverseSoftLimitEnabled(); }
	public void configMaxOutputVoltage(double voltage) { wrapped.configMaxOutputVoltage(voltage); }
	public void configPeakOutputVoltage(double forwardVoltage, double reverseVoltage) { wrapped.configPeakOutputVoltage(forwardVoltage, reverseVoltage); }
	public void configNominalOutputVoltage(double forwardVoltage, double reverseVoltage) { wrapped.configNominalOutputVoltage(forwardVoltage, reverseVoltage); }
	public void setParameter(com.ctre.CanTalonJNI.param_t paramEnum, double value) { wrapped.setParameter(paramEnum, value); }
	public double getParameter(com.ctre.CanTalonJNI.param_t paramEnum) { return wrapped.getParameter(paramEnum); }
	public void clearStickyFaults() { wrapped.clearStickyFaults(); }
	public void enableLimitSwitch(boolean forward, boolean reverse) { wrapped.enableLimitSwitch(forward, reverse); }
	public void ConfigFwdLimitSwitchNormallyOpen(boolean normallyOpen) { wrapped.ConfigFwdLimitSwitchNormallyOpen(normallyOpen); }
	public void ConfigRevLimitSwitchNormallyOpen(boolean normallyOpen) { wrapped.ConfigRevLimitSwitchNormallyOpen(normallyOpen); }
	public void enableBrakeMode(boolean brake) { wrapped.enableBrakeMode(brake); }
	public int getFaultOverTemp() { return wrapped.getFaultOverTemp(); }
	public int getFaultUnderVoltage() { return wrapped.getFaultUnderVoltage(); }
	public int getFaultForLim() { return wrapped.getFaultForLim(); }
	public int getFaultRevLim() { return wrapped.getFaultRevLim(); }
	public int getFaultHardwareFailure() { return wrapped.getFaultHardwareFailure(); }
	public int getFaultForSoftLim() { return wrapped.getFaultForSoftLim(); }
	public int getFaultRevSoftLim() { return wrapped.getFaultRevSoftLim(); }
	public int getStickyFaultOverTemp() { return wrapped.getStickyFaultOverTemp(); }
	public int getStickyFaultUnderVoltage() { return wrapped.getStickyFaultUnderVoltage(); }
	public int getStickyFaultForLim() { return wrapped.getStickyFaultForLim(); }
	public int getStickyFaultRevLim() { return wrapped.getStickyFaultRevLim(); }
	public int getStickyFaultForSoftLim() { return wrapped.getStickyFaultForSoftLim(); }
	public int getStickyFaultRevSoftLim() { return wrapped.getStickyFaultRevSoftLim(); }
	public void enableZeroSensorPositionOnIndex(boolean enable, boolean risingEdge) { wrapped.enableZeroSensorPositionOnIndex(enable, risingEdge); }
	public void enableZeroSensorPositionOnForwardLimit(boolean enable) { wrapped.enableZeroSensorPositionOnForwardLimit(enable); }
	public void enableZeroSensorPositionOnReverseLimit(boolean enable) { wrapped.enableZeroSensorPositionOnReverseLimit(enable); }
	public void changeMotionControlFramePeriod(int periodMs) { wrapped.changeMotionControlFramePeriod(periodMs); }
	public void clearMotionProfileTrajectories() { wrapped.clearMotionProfileTrajectories(); }
	public int getMotionProfileTopLevelBufferCount() { return wrapped.getMotionProfileTopLevelBufferCount(); }
	public boolean pushMotionProfileTrajectory(com.ctre.CANTalon.TrajectoryPoint trajPt) { return wrapped.pushMotionProfileTrajectory(trajPt); }
	public boolean isMotionProfileTopLevelBufferFull() { return wrapped.isMotionProfileTopLevelBufferFull(); }
	public void processMotionProfileBuffer() { wrapped.processMotionProfileBuffer(); }
	public void getMotionProfileStatus(com.ctre.CANTalon.MotionProfileStatus motionProfileStatus) { wrapped.getMotionProfileStatus(motionProfileStatus); }
	public void clearMotionProfileHasUnderrun() { wrapped.clearMotionProfileHasUnderrun(); }
	public void setMotionMagicCruiseVelocity(double motMagicCruiseVeloc) { wrapped.setMotionMagicCruiseVelocity(motMagicCruiseVeloc); }
	public void setMotionMagicAcceleration(double motMagicAccel) { wrapped.setMotionMagicAcceleration(motMagicAccel); }
	public double getMotionMagicCruiseVelocity() { return wrapped.getMotionMagicCruiseVelocity(); }
	public double getMotionMagicAcceleration() { return wrapped.getMotionMagicAcceleration(); }
	public void setCurrentLimit(int amps) { wrapped.setCurrentLimit(amps); }
	public void EnableCurrentLimit(boolean enable) { wrapped.EnableCurrentLimit(enable); }
	public int GetGadgeteerStatus(com.ctre.GadgeteerUartClient.GadgeteerUartStatus status) { return wrapped.GetGadgeteerStatus(status); }
	public void setExpiration(double timeout) { wrapped.setExpiration(timeout); }
	public double getExpiration() { return wrapped.getExpiration(); }
	public boolean isAlive() { return wrapped.isAlive(); }
	public boolean isSafetyEnabled() { return wrapped.isSafetyEnabled(); }
	public void setSafetyEnabled(boolean enabled) { wrapped.setSafetyEnabled(enabled); }
	public void initTable(edu.wpi.first.wpilibj.tables.ITable subtable) { wrapped.initTable(subtable); }
	public void updateTable() { wrapped.updateTable(); }
	public void startLiveWindowMode() { wrapped.startLiveWindowMode(); }
	public void stopLiveWindowMode() { wrapped.stopLiveWindowMode(); }

}
