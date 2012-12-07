package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.timeutils.PeakOffPeakTime;
import com.acmetelecom.timeutils.TimeCalculator;
import com.acmetelecom.customer.Tariff;

public class DefaultTotalBillCalculator implements TotalBillCalculator {

	private CallCostCalculator calculateCallCost;
	private LineItemFactory lineItemFactory; 
	private TimeCalculator timeCalculator;

	public DefaultTotalBillCalculator(TimeCalculator timeCalculator) {
		this.calculateCallCost = new DefaultCallCostCalculator();
		this.lineItemFactory = new CallLineItemFactory();
		this.timeCalculator = timeCalculator;
	}

	
	public DefaultTotalBillCalculator(final CallCostCalculator calculateCallCost, 
			final LineItemFactory lineItemFactory, TimeCalculator timeCalculator) {
		this.calculateCallCost = calculateCallCost;
		this.lineItemFactory = lineItemFactory;
		this.timeCalculator = timeCalculator;
	}

	public BigDecimal calculateTotalBill(List<Call> calls, Tariff tariff, List<LineItem> items) {

		BigDecimal totalBill = new BigDecimal(0);

		if (calls == null){
			return totalBill;
		}

		for (final Call call : calls) {
			if (call.getEndTime() == null){
				continue;
			}
			PeakOffPeakTime peakOffPeakTime = timeCalculator.calculateTimes(call.getStartTime(), call.getEndTime());
			BigDecimal callCost = calculateCallCost.calculateCost(peakOffPeakTime, tariff);
			totalBill = totalBill.add(callCost);
			LineItem lineItem = lineItemFactory.createCallLineItem(call, call.getCallee(), callCost, peakOffPeakTime);
			items.add(lineItem);
		}
		return totalBill;
	}
}
