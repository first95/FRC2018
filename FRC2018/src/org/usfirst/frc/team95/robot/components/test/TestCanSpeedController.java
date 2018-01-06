package org.usfirst.frc.team95.robot.components.test;

import org.usfirst.frc.team95.robot.components.CanTalonI;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.StatusFrameRate;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.TrajectoryPoint;
import com.ctre.CanTalonJNI.param_t;
import com.ctre.GadgeteerUartClient.GadgeteerUartStatus;

import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.tables.ITable;

public class TestCanSpeedController implements CanTalonI {
	private double setpoint;
	private double current;
	private int myId; // An arbitrary ID that identifies this object to its listener
	
	// An interface to be implemented by the class that owns these
	public interface Listener {
		void takeSet     (int id, double set  );
		void takeSpeed   (int id, double speed);
		void takePosition(int id, double pos  );
	}
	private Listener listener;
	
	public TestCanSpeedController(int id, Listener l) {
		listener = l;
		myId = id;
	}
	
	public double get() {
		return setpoint;
	}

	public void set(double speed) {
		listener.takeSet(myId, speed);
		setpoint = speed;
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setInverted(boolean isInverted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getError() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSetpoint(double setpoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reverseSensor(boolean flip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reverseOutput(boolean flip) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getEncPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setEncPosition(int newPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getEncVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPulseWidthPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPulseWidthPosition(int newPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPulseWidthVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPulseWidthRiseToFallUs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPulseWidthRiseToRiseUs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNumberOfQuadIdxRises() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPinStateQuadA() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPinStateQuadB() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPinStateQuadIdx() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAnalogPosition(int newPosition) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAnalogInPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAnalogInRaw() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAnalogInVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getClosedLoopError() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAllowableClosedLoopErr(int allowableCloseLoopError) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFwdLimitSwitchClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRevLimitSwitchClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZeroSensorPosOnIndexEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZeroSensorPosOnRevLimitEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isZeroSensorPosOnFwdLimitEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getBrakeEnableDuringNeutral() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configEncoderCodesPerRev(int codesPerRev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPotentiometerTurns(int turns) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOutputCurrent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOutputVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBusVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPosition(double pos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setControlMode(int mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeControlMode(TalonControlMode controlMode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFeedbackDevice(FeedbackDevice device) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatusFrameRateMs(StatusFrameRate stateFrame, int periodMs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableControl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disableControl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isControlEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getP() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getI() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getD() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getIZone() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getCloseLoopRampRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long GetFirmwareVersion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long GetIaccum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setP(double p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setI(double i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setD(double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setF(double f) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIZone(int izone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCloseLoopRampRate(double rampRate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVoltageRampRate(double rampRate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVoltageCompensationRampRate(double rampRate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ClearIaccum() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPID(double p, double i, double d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSetpoint() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setProfile(int profile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getDeviceID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearIAccum() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setForwardSoftLimit(double forwardLimit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getForwardSoftLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void enableForwardSoftLimit(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isForwardSoftLimitEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setReverseSoftLimit(double reverseLimit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getReverseSoftLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void enableReverseSoftLimit(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isReverseSoftLimitEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void configMaxOutputVoltage(double voltage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPeakOutputVoltage(double forwardVoltage, double reverseVoltage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configNominalOutputVoltage(double forwardVoltage, double reverseVoltage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParameter(param_t paramEnum, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getParameter(param_t paramEnum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clearStickyFaults() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableLimitSwitch(boolean forward, boolean reverse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ConfigFwdLimitSwitchNormallyOpen(boolean normallyOpen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ConfigRevLimitSwitchNormallyOpen(boolean normallyOpen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableBrakeMode(boolean brake) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFaultOverTemp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFaultUnderVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFaultForLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFaultRevLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFaultHardwareFailure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFaultForSoftLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFaultRevSoftLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStickyFaultOverTemp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStickyFaultUnderVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStickyFaultForLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStickyFaultRevLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStickyFaultForSoftLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getStickyFaultRevSoftLim() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void enableZeroSensorPositionOnIndex(boolean enable, boolean risingEdge) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableZeroSensorPositionOnForwardLimit(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableZeroSensorPositionOnReverseLimit(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMotionControlFramePeriod(int periodMs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearMotionProfileTrajectories() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMotionProfileTopLevelBufferCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean pushMotionProfileTrajectory(TrajectoryPoint trajPt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMotionProfileTopLevelBufferFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void processMotionProfileBuffer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getMotionProfileStatus(MotionProfileStatus motionProfileStatus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearMotionProfileHasUnderrun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMotionMagicCruiseVelocity(double motMagicCruiseVeloc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMotionMagicAcceleration(double motMagicAccel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getMotionMagicCruiseVelocity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMotionMagicAcceleration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCurrentLimit(int amps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void EnableCurrentLimit(boolean enable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int GetGadgeteerStatus(GadgeteerUartStatus status) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setExpiration(double timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getExpiration() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSafetyEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initTable(ITable subtable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startLiveWindowMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopLiveWindowMode() {
		// TODO Auto-generated method stub
		
	}
}
