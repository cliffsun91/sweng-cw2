package com.acmetelecom.telephonecallbuilder;

import org.joda.time.DateTime;

public interface StartTimeBuilder {
	
	public EndTimeBuilder withStartTime(DateTime time);
}
