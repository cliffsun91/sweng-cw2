package com.acmetelecom.callevent;

import org.joda.time.DateTime;

public class CallStart extends AbstractCallEvent {
    public CallStart(String caller, String callee, DateTime time) {
        super(caller, callee, time);
    }
    
    public static CallStart startCall(String caller, String callee, DateTime time){
    	return new CallStart(caller, callee, time);
    }
}
