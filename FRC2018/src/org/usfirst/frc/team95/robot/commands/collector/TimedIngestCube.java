package org.usfirst.frc.team95.robot.commands.collector;

public class TimedIngestCube extends RunChainsForDuration {
	public static final double INGEST_THROTTLE = -1.0;
	public static final double INGEST_TIME_S = 0.4;
	
	public TimedIngestCube() {
		super(INGEST_THROTTLE, INGEST_TIME_S);
	}
}
