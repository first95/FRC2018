package org.usfirst.frc.team95.robot.subsystems;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team95.robot.commands.vision.RunCameras;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends Subsystem {

	public UsbCamera camera;
	public CvSink cvSink;
	public CvSource outputStream;
	public Mat source;
	public Mat output;
	
	public Vision() {
		super();

		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setResolution(640, 480);

		CvSink cvSink = CameraServer.getInstance().getVideo();
		CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);

		Mat source = new Mat();
		Mat output = new Mat();

	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunCameras());
	}

	public void log() {

	}

}