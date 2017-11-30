package org.usfirst.frc.team95.robot.components.test;

import edu.wpi.first.wpilibj.CANSpeedController;
import edu.wpi.first.wpilibj.tables.ITable;

public class TestCanSpeedController implements CANSpeedController {
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
	
	@Override
	public double get() {
		return setpoint;
	}

	@Override
	public void set(double speed) {
		listener.takeSet(myId, speed);
		setpoint = speed;
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
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPID(double p, double i, double d) {
		// TODO Auto-generated method stub
		
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
	public void setSetpoint(double setpoint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getSetpoint() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getError() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void reset() {
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

	@Override
	public void initTable(ITable subtable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ITable getTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ControlMode getControlMode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setControlMode(int mode) {
		// TODO Auto-generated method stub
		
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
	public double getBusVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOutputVoltage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getOutputCurrent() {
		return current;
	}
	public void setOutputCurrent(double current) {
		this.current = current;
	}

	@Override
	public double getTemperature() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setVoltageRampRate(double rampRate) {
		// TODO Auto-generated method stub
		
	}

}
