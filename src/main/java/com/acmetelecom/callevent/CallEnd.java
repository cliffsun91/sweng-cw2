package com.acmetelecom.callevent;

import org.joda.time.DateTime;

public class CallEnd extends AbstractCallEvent {
    public CallEnd(String caller, String callee, DateTime time) {
        super(caller, callee, time.getMillis());
    }
}
