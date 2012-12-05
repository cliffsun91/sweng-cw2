package com.acmetelecom;


public class PeakOffPeakTime {
	private float totalPeakTime;
	private float totalOffPeakTime;

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

	public void updateTimes(PeakOffPeakTime peakOffPeakTime) {
		this.totalOffPeakTime += peakOffPeakTime.getTotalOffPeakTime();
		this.totalPeakTime += peakOffPeakTime.getTotalPeakTime();
	}
}
