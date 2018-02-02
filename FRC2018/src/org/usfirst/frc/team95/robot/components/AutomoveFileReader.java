package org.usfirst.frc.team95.robot.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AutomoveFileReader {

	private File automoveList = new File("C:/Users/TEST");
	private FileReader FR;

	public AutomoveFileReader() {
		fileReaderInit();
		fileReadContents();
	}

	public void fileReaderInit() {
		try {
			FR = new FileReader(automoveList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void fileReadContents() {

	}

}
