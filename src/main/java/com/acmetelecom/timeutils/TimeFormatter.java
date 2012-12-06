package com.acmetelecom.timeutils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeFormatter {
	
	public static String formatDateTime(DateTime time){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yy/M/d h:mma");
	    return time.toString(formatter);
	}
	
	public static String formatMinutesFromSeconds(long seconds){
		return "" + seconds / 60 + ":"
                + String.format("%02d", seconds % 60);
	}
}
