/*
 * TalonSrxWrapper.java
 * 
 * This class wraps the TalonSRX object from the CTRE Phoenix Framework.
 * It exists so that the AdjustedTalon class can extend something, without
 * knowing if it's extending a mock object or the real TalonSRX object.
 * 
 * 
 * Automatically generated with a series of N++ regular expressions.
 * Delete all bodies, then:
 * Find: (public void ([a-zA-Z]+)\([a-zA-Z0-9]+ ([a-zA-Z0-9]+), [a-zA-Z0-9]+ ([a-zA-Z0-9]+), [a-zA-Z0-9]+ ([a-zA-Z0-9]+)\)) $
 * Replace: \1 { wrapped.\2\(\3, \4, \5\); }
 * Repeat with lower argument counts.
 * Find: (public ([_a-zA-Z0-9]+) ([_0-9a-zA-Z]+)\([a-zA-Z0-9]+ ([a-zA-Z0-9]+), [a-zA-Z0-9]+ ([a-zA-Z0-9]+), [a-zA-Z0-9]+ ([a-zA-Z0-9]+), [a-zA-Z0-9]+ ([a-zA-Z0-9]+), [a-zA-Z0-9]+ ([a-zA-Z0-9]+)\)) $
 * Replace: \1 { return wrapped.\3\(\4, \5, \6, \7, \8\); }
 * Repeat with lower argument counts.
 */

package org.usfirst.frc.team95.robot.components;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlFrame;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.Faults;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.StickyFaults;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TalonSrxWrapper implements IMotorControllerEnhanced {
	private IMotorControllerEnhanced wrapped;
	
	public TalonSrxWrapper(int deviceNumber) {
		wrapped = new TalonSRX(deviceNumber);
	}
	
	public TalonSrxWrapper(IMotorControllerEnhanced wrapped) {
		this.wrapped = wrapped;
	}
	
	public int getClosedLoopTarget(int pidIdx) {
		if(wrapped instanceof TalonSRX) {
			if(((TalonSRX)wrapped).getControlMode() == ControlMode.Position ||
					((TalonSRX)wrapped).getControlMode() == ControlMode.Velocity) {
				return ((TalonSRX)wrapped).getClosedLoopTarget(pidIdx);
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	public ControlMode getControlMode() {
		if(wrapped instanceof TalonSRX) {
			return ((TalonSRX) wrapped).getControlMode();
		} else {
			return ControlMode.PercentOutput; // Return something somewhat reasonable
		}
	}
	
	@Override
	public void set(ControlMode Mode, double demand) { wrapped.set(Mode, demand); }
	@Override
	public void set(ControlMode Mode, double demand0, double demand1) { wrapped.set(Mode, demand0, demand1); }
	@Override
	public void neutralOutput() { wrapped.neutralOutput(); } 
	@Override
	public void setNeutralMode(NeutralMode neutralMode) { wrapped.setNeutralMode(neutralMode); }
	@Override
	public void setSensorPhase(boolean PhaseSensor) { wrapped.setSensorPhase(PhaseSensor); } 
	@Override
	public void setInverted(boolean invert) { wrapped.setInverted(invert); } 
	@Override
	public boolean getInverted() { return wrapped.getInverted(); }

	@Override
	public ErrorCode configOpenloopRamp(double secondsFromNeutralToFull, int timeoutMs) { return wrapped.configOpenloopRamp(secondsFromNeutralToFull, timeoutMs); }

	@Override
	public ErrorCode configClosedloopRamp(double secondsFromNeutralToFull, int timeoutMs) { return wrapped.configClosedloopRamp(secondsFromNeutralToFull, timeoutMs); }

	@Override
	public ErrorCode configPeakOutputForward(double percentOut, int timeoutMs) { return wrapped.configPeakOutputForward(percentOut, timeoutMs); }

	@Override
	public ErrorCode configPeakOutputReverse(double percentOut, int timeoutMs) { return wrapped.configPeakOutputReverse(percentOut, timeoutMs); }

	@Override
	public ErrorCode configNominalOutputForward(double percentOut, int timeoutMs) { return wrapped.configNominalOutputForward(percentOut, timeoutMs); }

	@Override
	public ErrorCode configNominalOutputReverse(double percentOut, int timeoutMs) { return wrapped.configNominalOutputReverse(percentOut, timeoutMs); }

	@Override
	public ErrorCode configNeutralDeadband(double percentDeadband, int timeoutMs) { return wrapped.configNeutralDeadband(percentDeadband, timeoutMs); }

	@Override
	public ErrorCode configVoltageCompSaturation(double voltage, int timeoutMs) { return wrapped.configVoltageCompSaturation(voltage, timeoutMs); }

	@Override
	public ErrorCode configVoltageMeasurementFilter(int filterWindowSamples, int timeoutMs) { return wrapped.configVoltageMeasurementFilter(filterWindowSamples, timeoutMs); }

	@Override
	public void enableVoltageCompensation(boolean enable) { wrapped.enableVoltageCompensation(enable); } 
	@Override
	public double getBusVoltage() { return wrapped.getBusVoltage(); }

	@Override
	public double getMotorOutputPercent() { return wrapped.getMotorOutputPercent(); }

	@Override
	public double getMotorOutputVoltage() { return wrapped.getMotorOutputVoltage(); }

	@Override
	public double getOutputCurrent() { return wrapped.getOutputCurrent(); }

	@Override
	public double getTemperature() { return wrapped.getTemperature(); }

	@Override
	public ErrorCode configSelectedFeedbackSensor(RemoteFeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) { return wrapped.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs); }

	@Override
	public ErrorCode configRemoteFeedbackFilter(int deviceID, RemoteSensorSource remoteSensorSource, int remoteOrdinal, int timeoutMs) { return wrapped.configRemoteFeedbackFilter(deviceID, remoteSensorSource, remoteOrdinal, timeoutMs); }

	@Override
	public ErrorCode configSensorTerm(SensorTerm sensorTerm, FeedbackDevice feedbackDevice, int timeoutMs) { return wrapped.configSensorTerm(sensorTerm, feedbackDevice, timeoutMs); }

	@Override
	public int getSelectedSensorPosition(int pidIdx) { return wrapped.getSelectedSensorPosition(pidIdx); }

	@Override
	public int getSelectedSensorVelocity(int pidIdx) { return wrapped.getSelectedSensorVelocity(pidIdx); }

	@Override
	public ErrorCode setSelectedSensorPosition(int sensorPos, int pidIdx, int timeoutMs) { return wrapped.setSelectedSensorPosition(sensorPos, pidIdx, timeoutMs); }

	@Override
	public ErrorCode setControlFramePeriod(ControlFrame frame, int periodMs) { return wrapped.setControlFramePeriod(frame, periodMs); }

	@Override
	public ErrorCode setStatusFramePeriod(StatusFrame frame, int periodMs, int timeoutMs) { return wrapped.setStatusFramePeriod(frame, periodMs, timeoutMs); }

	@Override
	public int getStatusFramePeriod(StatusFrame frame, int timeoutMs) { return wrapped.getStatusFramePeriod(frame, timeoutMs); }

	@Override
	public ErrorCode configForwardLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int deviceID, int timeoutMs) { return wrapped.configForwardLimitSwitchSource(type, normalOpenOrClose, deviceID, timeoutMs); }

	@Override
	public ErrorCode configReverseLimitSwitchSource(RemoteLimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int deviceID, int timeoutMs) { return wrapped.configReverseLimitSwitchSource(type, normalOpenOrClose, deviceID, timeoutMs); }

	@Override
	public void overrideLimitSwitchesEnable(boolean enable) { wrapped.overrideLimitSwitchesEnable(enable); } 
	@Override
	public ErrorCode configForwardSoftLimitThreshold(int forwardSensorLimit, int timeoutMs) { return wrapped.configForwardSoftLimitThreshold(forwardSensorLimit, timeoutMs); }

	@Override
	public ErrorCode configReverseSoftLimitThreshold(int reverseSensorLimit, int timeoutMs) { return wrapped.configReverseSoftLimitThreshold(reverseSensorLimit, timeoutMs); }

	@Override
	public ErrorCode configForwardSoftLimitEnable(boolean enable, int timeoutMs) { return wrapped.configForwardSoftLimitEnable(enable, timeoutMs); }

	@Override
	public ErrorCode configReverseSoftLimitEnable(boolean enable, int timeoutMs) { return wrapped.configReverseSoftLimitEnable(enable, timeoutMs); }

	@Override
	public void overrideSoftLimitsEnable(boolean enable) { wrapped.overrideSoftLimitsEnable(enable); } 
	@Override
	public ErrorCode config_kP(int slotIdx, double value, int timeoutMs) { return wrapped.config_kP(slotIdx, value, timeoutMs); }

	@Override
	public ErrorCode config_kI(int slotIdx, double value, int timeoutMs) { return wrapped.config_kI(slotIdx, value, timeoutMs); }

	@Override
	public ErrorCode config_kD(int slotIdx, double value, int timeoutMs) { return wrapped.config_kD(slotIdx, value, timeoutMs); }

	@Override
	public ErrorCode config_kF(int slotIdx, double value, int timeoutMs) { return wrapped.config_kF(slotIdx, value, timeoutMs); }

	@Override
	public ErrorCode config_IntegralZone(int slotIdx, int izone, int timeoutMs) { return wrapped.config_IntegralZone(slotIdx, izone, timeoutMs); }

	@Override
	public ErrorCode configAllowableClosedloopError(int slotIdx, int allowableCloseLoopError, int timeoutMs) { return wrapped.configAllowableClosedloopError(slotIdx, allowableCloseLoopError, timeoutMs); }

	@Override
	public ErrorCode configMaxIntegralAccumulator(int slotIdx, double iaccum, int timeoutMs) { return wrapped.configMaxIntegralAccumulator(slotIdx, iaccum, timeoutMs); }

	@Override
	public ErrorCode setIntegralAccumulator(double iaccum, int pidIdx, int timeoutMs) { return wrapped.setIntegralAccumulator(iaccum, pidIdx, timeoutMs); }

	@Override
	public int getClosedLoopError(int pidIdx) { return wrapped.getClosedLoopError(pidIdx); }

	@Override
	public double getIntegralAccumulator(int pidIdx) { return wrapped.getIntegralAccumulator(pidIdx); }

	@Override
	public double getErrorDerivative(int pidIdx) { return wrapped.getErrorDerivative(pidIdx); }

	@Override
	public void selectProfileSlot(int slotIdx, int pidIdx) { wrapped.selectProfileSlot(slotIdx, pidIdx); }
	@Override
	public int getActiveTrajectoryPosition() { return wrapped.getActiveTrajectoryPosition(); }

	@Override
	public int getActiveTrajectoryVelocity() { return wrapped.getActiveTrajectoryVelocity(); }

	@Override
	public double getActiveTrajectoryHeading() { return wrapped.getActiveTrajectoryHeading(); }

	@Override
	public ErrorCode configMotionCruiseVelocity(int sensorUnitsPer100ms, int timeoutMs) { return wrapped.configMotionCruiseVelocity(sensorUnitsPer100ms, timeoutMs); }

	@Override
	public ErrorCode configMotionAcceleration(int sensorUnitsPer100msPerSec, int timeoutMs) { return wrapped.configMotionAcceleration(sensorUnitsPer100msPerSec, timeoutMs); }

	@Override
	public ErrorCode clearMotionProfileTrajectories() { return wrapped.clearMotionProfileTrajectories(); }

	@Override
	public int getMotionProfileTopLevelBufferCount() { return wrapped.getMotionProfileTopLevelBufferCount(); }

	@Override
	public ErrorCode pushMotionProfileTrajectory(TrajectoryPoint trajPt) { return wrapped.pushMotionProfileTrajectory(trajPt); }

	@Override
	public boolean isMotionProfileTopLevelBufferFull() { return wrapped.isMotionProfileTopLevelBufferFull(); }

	@Override
	public void processMotionProfileBuffer() { wrapped.processMotionProfileBuffer(); } 
	@Override
	public ErrorCode getMotionProfileStatus(MotionProfileStatus statusToFill) { return wrapped.getMotionProfileStatus(statusToFill); }

	@Override
	public ErrorCode clearMotionProfileHasUnderrun(int timeoutMs) { return wrapped.clearMotionProfileHasUnderrun(timeoutMs); }

	@Override
	public ErrorCode changeMotionControlFramePeriod(int periodMs) { return wrapped.changeMotionControlFramePeriod(periodMs); }

	@Override
	public ErrorCode getLastError() { return wrapped.getLastError(); }

	@Override
	public ErrorCode getFaults(Faults toFill) { return wrapped.getFaults(toFill); }

	@Override
	public ErrorCode getStickyFaults(StickyFaults toFill) { return wrapped.getStickyFaults(toFill); }

	@Override
	public ErrorCode clearStickyFaults(int timeoutMs) { return wrapped.clearStickyFaults(timeoutMs); }

	@Override
	public int getFirmwareVersion() { return wrapped.getFirmwareVersion(); }

	@Override
	public boolean hasResetOccurred() { return wrapped.hasResetOccurred(); }

	@Override
	public ErrorCode configSetCustomParam(int newValue, int paramIndex, int timeoutMs) { return wrapped.configSetCustomParam(newValue, paramIndex, timeoutMs); }

	@Override
	public int configGetCustomParam(int paramIndex, int timoutMs) { return wrapped.configGetCustomParam(paramIndex, timoutMs); }

	@Override
	public ErrorCode configSetParameter(ParamEnum param, double value, int subValue, int ordinal, int timeoutMs) { return wrapped.configSetParameter(param, value, subValue, ordinal, timeoutMs); }

	@Override
	public ErrorCode configSetParameter(int param, double value, int subValue, int ordinal, int timeoutMs) { return wrapped.configSetParameter(param, value, subValue, ordinal, timeoutMs); }

	@Override
	public double configGetParameter(ParamEnum paramEnum, int ordinal, int timeoutMs) { return wrapped.configGetParameter(paramEnum, ordinal, timeoutMs); }

	@Override
	public double configGetParameter(int paramEnum, int ordinal, int timeoutMs) { return wrapped.configGetParameter(paramEnum, ordinal, timeoutMs); }

	@Override
	public int getBaseID() { return wrapped.getBaseID(); }

	@Override
	public int getDeviceID() { return wrapped.getDeviceID(); }

	@Override
	public void follow(IMotorController masterToFollow) { wrapped.follow(masterToFollow); } 
	@Override
	public void valueUpdated() { wrapped.valueUpdated(); } 
	@Override
	public ErrorCode configSelectedFeedbackSensor(FeedbackDevice feedbackDevice, int pidIdx, int timeoutMs) { return wrapped.configSelectedFeedbackSensor(feedbackDevice, pidIdx, timeoutMs); }

	@Override
	public ErrorCode setStatusFramePeriod(StatusFrameEnhanced frame, int periodMs, int timeoutMs) { return wrapped.setStatusFramePeriod(frame, periodMs, timeoutMs); }

	@Override
	public int getStatusFramePeriod(StatusFrameEnhanced frame, int timeoutMs) { return wrapped.getStatusFramePeriod(frame, timeoutMs); }

	@Override
	public ErrorCode configVelocityMeasurementPeriod(VelocityMeasPeriod period, int timeoutMs) { return wrapped.configVelocityMeasurementPeriod(period, timeoutMs); }

	@Override
	public ErrorCode configVelocityMeasurementWindow(int windowSize, int timeoutMs) { return wrapped.configVelocityMeasurementWindow(windowSize, timeoutMs); }

	@Override
	public ErrorCode configForwardLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int timeoutMs) { return wrapped.configForwardLimitSwitchSource(type, normalOpenOrClose, timeoutMs); }

	@Override
	public ErrorCode configReverseLimitSwitchSource(LimitSwitchSource type, LimitSwitchNormal normalOpenOrClose, int timeoutMs) { return wrapped.configReverseLimitSwitchSource(type, normalOpenOrClose, timeoutMs); }

	@Override
	public ErrorCode configPeakCurrentLimit(int amps, int timeoutMs) { return wrapped.configPeakCurrentLimit(amps, timeoutMs); }

	@Override
	public ErrorCode configPeakCurrentDuration(int milliseconds, int timeoutMs) { return wrapped.configPeakCurrentDuration(milliseconds, timeoutMs); }

	@Override
	public ErrorCode configContinuousCurrentLimit(int amps, int timeoutMs) { return wrapped.configContinuousCurrentLimit(amps, timeoutMs); }

	@Override
	public void enableCurrentLimit(boolean enable) { wrapped.enableCurrentLimit(enable); } 
}
