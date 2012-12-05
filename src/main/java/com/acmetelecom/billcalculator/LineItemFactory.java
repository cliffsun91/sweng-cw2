package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.CallTime;
import com.acmetelecom.LineItem;
import com.acmetelecom.PeakOffPeakTime;

public interface LineItemFactory {

	LineItem createCallTimeLineItem(CallTime call, String callee, BigDecimal callCost, PeakOffPeakTime peakOffPeakTime);
	
}
