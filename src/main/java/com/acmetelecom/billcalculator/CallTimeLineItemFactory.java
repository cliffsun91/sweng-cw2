package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.CallTimeLineItem;
import com.acmetelecom.TimeUtils.PeakOffPeakTime;

public class CallTimeLineItemFactory implements LineItemFactory{

	@Override
	public CallTimeLineItem createCallTimeLineItem(CallTime call, String callee, BigDecimal callCost, PeakOffPeakTime peakOffPeakTime){
		return new CallTimeLineItem(call, callee, callCost, peakOffPeakTime);
	}
}
