package com.acmetelecom.callevent;

public class CallStart extends AbstractCallEvent {
    public CallStart(String caller, String callee) {
        super(caller, callee, System.currentTimeMillis());
    }
}
