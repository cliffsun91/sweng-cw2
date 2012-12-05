package com.acmetelecom;

import org.joda.time.DateTime;


public class CallStartTime {
	private DateTime startTime;

	public CallStartTime(DateTime startTime){
		this.startTime = startTime;
	}
	
	public DateTime getStartTime() {
		return startTime;
	}
}
