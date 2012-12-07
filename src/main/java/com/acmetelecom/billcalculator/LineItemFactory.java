package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.call.Call;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.timeutils.PeakOffPeakTime;

public interface LineItemFactory {

	LineItem createCallLineItem(Call call, String callee, BigDecimal callCost, PeakOffPeakTime peakOffPeakTime);
	
}
