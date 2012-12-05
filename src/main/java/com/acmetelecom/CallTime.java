package com.acmetelecom;

import org.joda.time.DateTime;


public class CallTime {
	private DateTime startTime;
	private DateTime endTime;

	public CallTime(DateTime startTime){
		this.startTime = startTime;
	}
	
	public DateTime getStartTime() {
		return startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
}
