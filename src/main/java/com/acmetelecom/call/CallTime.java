package com.acmetelecom.call;

import org.joda.time.DateTime;


public class CallTime {
	private DateTime startTime;
	private DateTime endTime;
	private String caller;
	private String callee;

	public CallTime(final DateTime startTime, final String caller, final String callee){
		this(startTime, null, caller, callee);
	}
	
	public CallTime(final DateTime startTime, DateTime endTime, final String caller, final String callee){
		this.startTime = startTime;
		this.endTime = endTime;
		this.callee = callee;
		this.caller = caller;
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
	
	public String getCaller() {
		return caller;
	}
}
