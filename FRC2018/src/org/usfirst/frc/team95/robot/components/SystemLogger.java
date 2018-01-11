package org.usfirst.frc.team95.robot.components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemLogger
	{

		private String systemDate;
		private String systemDay;
		private String systemMonth;
		private String systemNoDay;
		private String systemHour;
		private String systemMinute;
		private String systemSecond;
		private String systemTimeZone;
		private String systemYear;

		private Date finalDate;
		private Date currentDate;
		private SimpleDateFormat dateFormat;

		private String logFileTitleCSV;
		private File logFileCSV;
		private FileWriter fWCSV;

		public SystemLogger() throws IOException
			{
				System.out.println("-- Attempting to start System Logger --");
				SystemLoggerInit();
				System.out.println("-- System Logger initalized --");
			}

		private void SystemLoggerInit() throws IOException
			{
				currentDate = new Date();
				systemDate = currentDate.toString();

				systemDay = systemDate.substring(1, 3);
				systemMonth = systemDate.substring(5, 7);
				systemNoDay = systemDate.substring(9, 10);
				systemHour = systemDate.substring(12, 13);
				systemMinute = systemDate.substring(15, 16);
				systemSecond = systemDate.substring(18, 19);
				systemTimeZone = systemDate.substring(21, 23);
				systemYear = systemDate.substring(25, 28);
				
				systemDate = systemYear + systemMonth + systemDay + "-" + systemHour + systemMinute + systemSecond;

				dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				try
					{
						finalDate = dateFormat.parse(systemDate);
					}
				catch (ParseException e)
					{
						System.out.println("-- Error parsing date --");
						System.out.println("ERROR:");
						e.printStackTrace();
					}

				logFileTitleCSV = finalDate.toString();
				
				File logFileCSV = new File("/home/lvuser/Logs/" + logFileTitleCSV + ".csv");
				
				fWCSV = new FileWriter(logFileCSV);
				
				fWCSV.write("test, test");
			}

		public void SystemLoggerWrite(String mData)
			{

				try
					{
						fWCSV.write(mData + "\n");
						fWCSV.flush();
					}
				catch (Exception e)
					{
						System.out.println("-- Error writing to file --");
						System.out.println("ERROR:");
						e.printStackTrace();
					}

			}

		public void SystemLoggerNullCheck(Double doubleNullCheckHolder[], Boolean booleanNullCheckHolder[], String stringNullCheckHolder)
			{

				for (Double i : doubleNullCheckHolder)
					{
						if (i == null)
							{
								i = 0.0;
							}
					}

				for (Boolean i : booleanNullCheckHolder)
					{
						if (i == null)
							{
								i = false;
							}
					}

				if (stringNullCheckHolder == null)
					{
						stringNullCheckHolder = "";
					}

			}

		public void SystemLoggerClose()
			{
				try
					{
						fWCSV.close();
					}
				catch (IOException e)
					{
						System.out.println("-- Error Closing System Logger --");
						System.out.println("ERROR:");
						e.printStackTrace();
					}
			}
	}
