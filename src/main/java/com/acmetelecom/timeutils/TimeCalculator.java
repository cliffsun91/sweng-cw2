package com.acmetelecom.timeutils;

import org.joda.time.DateTime;

public interface TimeCalculator{

	PeakOffPeakTime calculateTimes(DateTime startTime, DateTime endTime);
}
