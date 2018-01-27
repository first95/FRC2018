
package org.usfirst.frc.team95.robot.commands;

import org.usfirst.frc.team95.robot.Robot;
import org.usfirst.frc.team95.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;

public class ShiftGear extends Command
	{

		DriveBase driveBase = new DriveBase();

		public ShiftGear()
			{
			requires(Robot.drivebase);
			}
		
		@Override
		protected void execute() {

				if (false)
					{
						Robot.drivebase.setGear(false);
					}
				else
					{
						Robot.drivebase.setGear(true);
					}
			}

		@Override
		protected boolean isFinished()
			{
				// TODO Auto-generated method stub
				return false;
			}

	}
