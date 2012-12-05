package com.acmetelecom.callevent;

import org.joda.time.DateTime;

public interface CallEvent {

	public String getCaller();

	public String getCallee();

	public DateTime getTimestamp();

}