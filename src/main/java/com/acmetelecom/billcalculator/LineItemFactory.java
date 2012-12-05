package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.timeUtils.PeakOffPeakTime;

public interface LineItemFactory {

	LineItem createCallTimeLineItem(CallTime call, String callee, BigDecimal callCost, PeakOffPeakTime peakOffPeakTime);
	
}
