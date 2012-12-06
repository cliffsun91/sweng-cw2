package com.acmetelecom.timeutils;

import org.joda.time.DateTime;

public interface ITimeCalculator{

	PeakOffPeakTime calculateTimes(DateTime startTime, DateTime endTime);
}
