/*
 * SolenoidI.java
 * 
 * This interface just has the same methods as the WpiLib Solenoid object.
 * It's used to promise that a particular object will provide the same
 * methods as the Solenoid object.
 * 
 */
package org.usfirst.frc.team95.robot.components;

public interface SolenoidI {

  public void set(boolean on);
  public boolean get() ;
  
  public boolean isBlackListed();
  public void setPulseDuration(double durationSeconds);
  public void startPulse();
}
