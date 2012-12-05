package com.acmetelecom;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.acmetelecom.callevent.CallEvent;

public class PeakOffPeakTime {
	private final float totalPeakTime;
	private final float totalOffPeakTime;

	public PeakOffPeakTime(float totalPeakTime, float totalOffPeakTime){
		this.totalOffPeakTime = 0;
		this.totalPeakTime = 0;
	}

	public float getTotalPeakTime() {
		return totalPeakTime;
	}

	public float getTotalOffPeakTime() {
		return totalOffPeakTime;
	}
}
