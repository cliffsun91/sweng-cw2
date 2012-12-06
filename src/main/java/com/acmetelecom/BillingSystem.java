package com.acmetelecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.billcalculator.CustomerBillGenerator;
import com.acmetelecom.call.CallTime;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.printer.HtmlPrinter;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.ITimeCalculator;
import com.acmetelecom.timeutils.TimeCalculator;

public class BillingSystem {

	private final HashMap<String, List<CallTime>> customerCurrentCallLog;
	private final CustomerBillGenerator customerBillGenerator;

	public BillingSystem() {
		this(HtmlPrinter.getInstance());
	}

	public BillingSystem(Printer printer) {		
		this(printer, new TimeCalculator());
	}
	
	public BillingSystem(Printer printer, ITimeCalculator timeCalculator) {
		this.customerCurrentCallLog = new HashMap<String, List<CallTime>>();
		this.customerBillGenerator = new CustomerBillGenerator(printer, timeCalculator);
	}

	public void callInitiated(String caller, String callee){
		CallTime call = new CallTime(DateTime.now(), caller, callee);
		fullCompletedCall(call);
	}

	public void callCompleted(String caller, String callee){
		try {
			List<CallTime> calls = customerCurrentCallLog.get(caller);
			CallTime time = calls.get(calls.size()-1);
			if (time.getEndTime() == null){ 
				time.setEndTime(DateTime.now());
			}
		} catch (Exception e) {
			// Won't throw exception to preserve original behaviour
		}
	}
	
	public void createCustomerBills(){
		createCustomerBills(CentralCustomerDatabase.getInstance().getCustomers());
	}

	private void createCustomerBills(final List<Customer> customers) {
		for (final Customer customer : customers) {
			final List<CallTime> calls = customerCurrentCallLog.get(customer.getPhoneNumber());
			final Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);
			customerBillGenerator.createBillFor(customer, calls, tariff);
			customerCurrentCallLog.put(customer.getPhoneNumber(), new ArrayList<CallTime>());
		}
	}
	
	public void fullCompletedCall(CallTime call){
		String caller = call.getCaller();
		if (customerCurrentCallLog.get(caller) == null){
			customerCurrentCallLog.put(caller, new ArrayList<CallTime>());
		}
		List<CallTime> calls = customerCurrentCallLog.get(caller);
		calls.add(call);
	}
}
