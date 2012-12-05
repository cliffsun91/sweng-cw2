package com.acmetelecom.timeUtils;

import org.joda.time.DateTime;

public interface ITimeCalculator{

	PeakOffPeakTime calculateTimes(DateTime startTime, DateTime endTime);
}
