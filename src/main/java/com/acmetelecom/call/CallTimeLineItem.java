package com.acmetelecom.call;

import java.math.BigDecimal;

import com.acmetelecom.TimeUtils.PeakOffPeakTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created with IntelliJ IDEA.
 * User: oaj09
 * Date: 02/12/12
 * Time: 00:43
 * To change this template use File | Settings | File Templates.
 */
public class CallTimeLineItem implements LineItem {

	private final CallTime call;
	private String callee;
    private final BigDecimal callCost;
	private PeakOffPeakTime peakOffPeakTime;
 
    public CallTimeLineItem(CallTime call, String callee, BigDecimal callCost, PeakOffPeakTime peakOffPeakTime) {
    	   this.call = call;
           this.callee = callee;
           this.callCost = callCost; 
           this.peakOffPeakTime = peakOffPeakTime;
	}

	public String durationMinutes() {
        return "" + peakOffPeakTime.durationSeconds() / 60 + ":"
                + String.format("%02d", peakOffPeakTime.durationSeconds() % 60);
    }

	@Override
	public String date() {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yy/M/d h:mma");
		return call.getStartTime().toString(formatter);
	}

	@Override
	public String callee() {
		return this.callee;
	}

	@Override
	public BigDecimal cost() {
		return this.callCost;
	}
}
