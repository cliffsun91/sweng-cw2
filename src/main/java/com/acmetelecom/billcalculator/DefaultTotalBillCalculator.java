package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.timeutils.PeakOffPeakTime;
import com.acmetelecom.timeutils.ITimeCalculator;
import com.acmetelecom.customer.Tariff;

public class DefaultTotalBillCalculator implements TotalBillCalculator {

	private CallCostCalculator calculateCallCost;
	private LineItemFactory lineItemFactory; 
	private ITimeCalculator timeCalculator;

	public DefaultTotalBillCalculator(ITimeCalculator timeCalculator) {
		this.calculateCallCost = new DefaultCallCostCalculator();
		this.lineItemFactory = new CallTimeLineItemFactory();
		this.timeCalculator = timeCalculator;
	}

	
	public DefaultTotalBillCalculator(final CallCostCalculator calculateCallCost, 
			final LineItemFactory lineItemFactory, ITimeCalculator timeCalculator) {
		this.calculateCallCost = calculateCallCost;
		this.lineItemFactory = lineItemFactory;
		this.timeCalculator = timeCalculator;
	}

	public BigDecimal calculateTotalBill(List<CallTime> calls, Tariff tariff, List<LineItem> items) {

		BigDecimal totalBill = new BigDecimal(0);

		if (calls == null){
			return totalBill;
		}

		for (final CallTime call : calls) {
			PeakOffPeakTime peakOffPeakTime = timeCalculator.calculateTimes(call.getStartTime(), call.getEndTime());
			BigDecimal callCost = calculateCallCost.calculateCost(peakOffPeakTime, tariff);
			totalBill = totalBill.add(callCost);
			LineItem lineItem = lineItemFactory.createCallTimeLineItem(call, call.getCallee(), callCost, peakOffPeakTime);
			items.add(lineItem);
		}
		return totalBill;
	}
}
