package com.acmetelecom.callevent;

public class CallEnd extends AbstractCallEvent {
    public CallEnd(String caller, String callee) {
        super(caller, callee, System.currentTimeMillis());
    }
}
