package com.telecom.telephonecallbuilder;

import org.joda.time.DateTime;

public interface EndTimeBuilder {
	
	public FinalTelephoneCallBuilder andWithEndTime(DateTime time);
}
