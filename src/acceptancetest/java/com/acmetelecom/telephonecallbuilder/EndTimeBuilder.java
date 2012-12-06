package com.acmetelecom.telephonecallbuilder;

import org.joda.time.DateTime;

public interface EndTimeBuilder {
	
	public FinalTelephoneCallBuilder andWithEndTime(DateTime time);
}
