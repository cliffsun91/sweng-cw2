package com.acmetelecom;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.acmetelecom.billcalculator.TotalBillCalculator;
import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.moneyformatters.MoneyFormatter;
import com.acmetelecom.printer.BillGenerator;
import com.acmetelecom.printer.HtmlPrinter;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.ITimeCalculator;
import com.acmetelecom.timeutils.TimeCalculator;

public class BillingSystem {

	private final HashMap<String, List<CallTime>> customerCurrentCallLog;
	private final BillGenerator billGenerator;
	private final TotalBillCalculator totalBillCalculator;

	public BillingSystem() {
		this(HtmlPrinter.getInstance());
	}

	public BillingSystem(Printer printer) {		
		this(printer, new TimeCalculator());
	}
	
	public BillingSystem(Printer printer, ITimeCalculator timeCalculator) {
		this.customerCurrentCallLog = new HashMap<String, List<CallTime>>();
		this.totalBillCalculator = new TotalBillCalculator(timeCalculator);
		billGenerator = new BillGenerator(new MoneyFormatter(), printer);
	}

	public void callInitiated(String caller, String callee){
		callInitiated(new CallStart(caller, callee));
	}

	public void callCompleted(String caller, String callee){
		callCompleted(new CallEnd(caller, callee));
	}

	public void callInitiated(CallStart startCall) {
		String caller = startCall.getCaller();
		String callee = startCall.getCallee();
		if (customerCurrentCallLog.get(caller) == null){
			customerCurrentCallLog.put(caller, new ArrayList<CallTime>());
		}
		List<CallTime> calls = customerCurrentCallLog.get(caller);
		calls.add(new CallTime(startCall.getTimestamp(), callee));
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
		final Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(
				customer);
		
		List<CallTime> calls = customerCurrentCallLog.get(customer.getPhoneNumber());
		BigDecimal totalBill = totalBillCalculator.calculateTotalBill(calls, tariff, items);

		String totalBillString = new MoneyFormatter().penceToPounds(totalBill);

		billGenerator.print(customer, items, totalBillString);
	}

}
