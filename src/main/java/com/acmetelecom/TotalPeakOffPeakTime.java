package com.acmetelecom;


public class TotalPeakOffPeakTime {
	private final float totalPeakTime;
	private final float totalOffPeakTime;

	public TotalPeakOffPeakTime(float totalPeakTime, float totalOffPeakTime){
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
