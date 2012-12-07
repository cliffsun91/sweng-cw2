package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.call.Call;
import com.acmetelecom.call.CallLineItem;
import com.acmetelecom.timeutils.PeakOffPeakTime;

public class CallLineItemFactory implements LineItemFactory{

	@Override
	public CallLineItem createCallLineItem(Call call, String callee, BigDecimal callCost, PeakOffPeakTime peakOffPeakTime){
		return new CallLineItem(call, callee, callCost, peakOffPeakTime);
	}
}
