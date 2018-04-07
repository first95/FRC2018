package org.usfirst.frc.team95.robot.commands.climber;

import org.usfirst.frc.team95.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ManuallyControlClimber extends Command {
	
	public enum ClimberHoldPoint {
		HERE, // Not a specific location - indicates holding whatever point the elevator is at
				// now.
	};
	
	private boolean wasHoldingPresentPositionLastIteration = false;

	public ManuallyControlClimber() {
		// This method is run once during robot startup
		requires(Robot.climber);
	}

	@Override
	public synchronized void start() {
		 // This method is called once when the command is activated
		 seekHoldPoint(ClimberHoldPoint.HERE);
		 wasHoldingPresentPositionLastIteration = true;
	}

	@Override
	protected void execute() {
		// This method is called every iteration

		 final String CLIMBER_MODE = "Climber mode";
		 // First priority: Is the stick outside the deadband?
		 if (Math.abs(Robot.oi.getClimberSpeed()) > 0) {
			 SmartDashboard.putString(CLIMBER_MODE, "Set speed");
			 Robot.climber.setClimberSpeed(Robot.oi.getClimberSpeed());
			 wasHoldingPresentPositionLastIteration = false;
		 } else {
		 // Second priority: hold the present position
		 if (!wasHoldingPresentPositionLastIteration) {
			 seekHoldPoint(ClimberHoldPoint.HERE);
			  System.out.println("TEST THE CLIMBER HOLD POINT IS: " + ClimberHoldPoint.HERE);
			 wasHoldingPresentPositionLastIteration = true;
		 } else {
			 // We already commanded the elevator to hold its present
			 // position, so we don't need to command it to do so again.
		 }
		 	SmartDashboard.putString(CLIMBER_MODE, "Hold present position");
		 }

	}

	@Override
	public synchronized void cancel() {
		// Cancel any position seeking
		Robot.climber.stopMotor();
	}

	@Override
	protected boolean isFinished() {
		return false; // until interrupted
	}

	 private void seekHoldPoint(ClimberHoldPoint point) {
		 double desiredHeightFeet = 0;
		 switch (point) {
			 case HERE: // Fallthrough - the HERE and default cases have the same action
			 default:
			 desiredHeightFeet = Robot.climber.getClimberHeightFeet();
			 break;
		 }
		
		 Robot.climber.setClimberHeight(desiredHeightFeet);
	 }

}
