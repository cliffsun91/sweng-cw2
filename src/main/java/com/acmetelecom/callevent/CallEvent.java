package com.acmetelecom.callevent;

public interface CallEvent {

	public String getCaller();

	public String getCallee();

	public long time();

}