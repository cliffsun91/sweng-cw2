package com.acmetelecom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.acmetelecom.moneyformatters.MoneyFormatter;
import com.acmetelecom.timeutils.PeakOffPeakTime;
import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.CallTimeLineItem;

import com.acmetelecom.timeutils.TimeCalculator;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.printer.BillPrinter;
import com.acmetelecom.printer.HtmlPrinter;
import com.acmetelecom.printer.Printer;

public class BillingSystem {

	private final HashMap<String, List<CallTime>> customerCurrentCallLog;
	private final Printer printer;

	public BillingSystem() {
		this(HtmlPrinter.getInstance());
	}
	
	public BillingSystem(Printer printer) {
		this.customerCurrentCallLog = new HashMap<String, List<CallTime>>();
		this.printer = printer;
	}
	
	public void callInitiated(String caller, String callee){
		callInitiated(new CallStart(caller, callee));
	}
	
	public void callCompleted(String caller, String callee){
		callCompleted(new CallEnd(caller, callee));
	}

	public void callInitiated(CallStart startCall) {
	    String caller = startCall.getCaller();
		if (customerCurrentCallLog.get(caller) == null){
			customerCurrentCallLog.put(caller, new ArrayList<CallTime>());
		}
		List<CallTime> calls = customerCurrentCallLog.get(caller);
		calls.add(new CallTime(startCall.getTimestamp()));
	}

	public void callCompleted(CallEnd endCall) {
		String caller = endCall.getCaller();
		List<CallTime> calls = customerCurrentCallLog.get(caller);
		CallTime time = calls.get(calls.size()-1);
		time.setEndTime(endCall.getTimestamp());
	}
	
	public void createCustomerBills(){
		createCustomerBills(CentralCustomerDatabase.getInstance().getCustomers());
	}

	public void createCustomerBills(final List<Customer> customers) {
		for (final Customer customer : customers) {
			createBillFor(customer);
			customerCurrentCallLog.put(customer.getPhoneNumber(), new ArrayList<CallTime>());
		}
	}

	private void createBillFor(final Customer customer) {	
		final List<LineItem> items = new ArrayList<LineItem>();
		BigDecimal totalBill = calculateTotalBill(customer, items);

		String totalBillString = new MoneyFormatter().penceToPounds(totalBill);

		new BillPrinter(new MoneyFormatter()).print(customer, items,
				totalBillString, printer);
	}

	private BigDecimal calculateTotalBill(final Customer customer,
			final List<LineItem> items) {
		String caller = customer.getPhoneNumber();
		List<CallTime> calls = customerCurrentCallLog.get(caller);
		final Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(
				customer);

		BigDecimal totalBill = new BigDecimal(0);
		if (calls == null){
			return totalBill;
		}
		
		for (final CallTime call : calls) {
			PeakOffPeakTime peakOffPeakTime = (new TimeCalculator()).calculateTimes(call.getStartTime(), call.getEndTime());
			BigDecimal callCost = calculateCost(peakOffPeakTime, tariff);
			callCost = callCost.setScale(0, RoundingMode.HALF_UP);

			totalBill = totalBill.add(callCost);
			items.add(new CallTimeLineItem(call, caller, callCost, peakOffPeakTime));
		}
		return totalBill;
	}

	private BigDecimal calculateCost(final PeakOffPeakTime peakOffPeakTime, final Tariff tariff) {
		BigDecimal peakCost = new BigDecimal(peakOffPeakTime.getPeakTime()).multiply(tariff.peakRate());
		BigDecimal offPeakCost = new BigDecimal(peakOffPeakTime.getOffPeakTime()).multiply(tariff.offPeakRate());
		return peakCost.add(offPeakCost);
	}

	public Printer getPrinter(){
		return printer;
	}

}
