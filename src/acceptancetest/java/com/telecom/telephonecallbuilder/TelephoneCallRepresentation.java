package com.telecom.telephonecallbuilder;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.acmetelecom.microtype.Person;

public class TelephoneCallRepresentation {

	private Person caller;
	private Person callee;
	private DateTime startTime;
	private DateTime endTime;
	
	public TelephoneCallRepresentation(Person caller, Person callee, DateTime startTime, DateTime endTime) {
		this.caller = caller;
		this.callee = callee;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public String getCallerName(){
		return caller.getName();
	}
	
	public String getCalleeName(){
		return callee.getName();
	}
	
	public String getStartTimeRepresentation(){
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yy/M/d h:mma");
	    return startTime.toString(formatter);
	}
	
	public String getDurationMinutes(){
		PeriodFormatter minutesAndSeconds = new PeriodFormatterBuilder()
		.appendMinutes()
		.appendSecondsWithMillis()
		.toFormatter();
		return minutesAndSeconds.print((new Duration(startTime, endTime)).toPeriod());
	}
}
