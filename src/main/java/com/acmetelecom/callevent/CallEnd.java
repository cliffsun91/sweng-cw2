package com.acmetelecom.callevent;

import org.joda.time.DateTime;

public class CallEnd extends AbstractCallEvent {
    public CallEnd(String caller, String callee, DateTime timestamp) {
        super(caller, callee, timestamp);
    }
    
    public static CallEnd endCall(String caller, String callee, DateTime timestamp){
    	return new CallEnd(caller, callee, timestamp);
    }
}
