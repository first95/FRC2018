package org.usfirst.frc.team95.robot.components;

public interface SolenoidI {

  public void set(boolean on);
  public boolean get() ;
  
  public boolean isBlackListed();
  public void setPulseDuration(double durationSeconds);
  public void startPulse();
}
