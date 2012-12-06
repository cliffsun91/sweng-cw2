package com.acmetelecom.call;

import org.joda.time.DateTime;


public class CallTime {
	private DateTime startTime;
	private DateTime endTime;
	private String callee;

	public CallTime(final DateTime startTime, final String callee){
		this.startTime = startTime;
		this.callee = callee;
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

	public String getCallee() {
		return callee;
	}
}
