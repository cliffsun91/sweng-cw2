package com.acmetelecom.callevent;

import org.joda.time.DateTime;

public abstract class AbstractCallEvent implements CallEvent {
    private String caller;
    private String callee;
    private DateTime timestamp;

    public AbstractCallEvent(String caller, String callee, DateTime timeStamp) {
        this.caller = caller;
        this.callee = callee;
        this.timestamp = timeStamp;
    }

    @Override
	public String getCaller() {
        return caller;
    }

    @Override
	public String getCallee() {
        return callee;
    }

    @Override
	public DateTime getTimestamp() {
        return timestamp;
    }
}
