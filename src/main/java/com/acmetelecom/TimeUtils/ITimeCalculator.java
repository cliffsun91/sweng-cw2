package com.acmetelecom.TimeUtils;

import org.joda.time.DateTime;

import com.acmetelecom.PeakOffPeakTime;

public interface ITimeCalculator{

	PeakOffPeakTime calculateTimes(DateTime startTime, DateTime endTime);
}
