package org.usfirst.frc.team95.robot.commands.vision;

import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team95.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.command.Command;

public class RunCameras extends Command {

	
	Vision visionServer = new Vision();
	
	public RunCameras() {

	}

	@Override
	protected void execute() {
		super.execute();
		visionServer.cvSink.grabFrame(visionServer.source);
		Imgproc.cvtColor(visionServer.source, visionServer.output, Imgproc.COLOR_BGR2GRAY);
		visionServer.outputStream.putFrame(visionServer.output);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
