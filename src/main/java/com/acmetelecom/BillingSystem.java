package com.acmetelecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.billcalculator.CustomerBillGenerator;
import com.acmetelecom.call.Call;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.printer.HtmlPrinter;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.ITimeCalculator;
import com.acmetelecom.timeutils.TimeCalculator;

public class BillingSystem {

	private final HashMap<String, List<Call>> customerCurrentCallLog;
	private final CustomerBillGenerator customerBillGenerator;

	public BillingSystem() {
		this(HtmlPrinter.getInstance());
	}

	public BillingSystem(Printer printer) {		
		this(printer, new TimeCalculator());
	}
	
	public BillingSystem(Printer printer, ITimeCalculator timeCalculator) {
		this.customerCurrentCallLog = new HashMap<String, List<Call>>();
		this.customerBillGenerator = new CustomerBillGenerator(printer, timeCalculator);
	}

	public void callInitiated(String caller, String callee){
		Call call = new Call(DateTime.now(), caller, callee);
		fullCompletedCall(call);
	}

	public void callCompleted(String caller, String callee){
		try {
			List<Call> calls = customerCurrentCallLog.get(caller);
			Call time = calls.get(calls.size()-1);
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

	public void createCustomerBills(final List<Customer> customers) {
		for (final Customer customer : customers) {
			final List<Call> calls = customerCurrentCallLog.get(customer.getPhoneNumber());
			final Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(customer);
			customerBillGenerator.createBillFor(customer, calls, tariff);
			customerCurrentCallLog.put(customer.getPhoneNumber(), new ArrayList<Call>());
		}
	}
	
	public void fullCompletedCall(Call call){
		String caller = call.getCaller();
		if (customerCurrentCallLog.get(caller) == null){
			customerCurrentCallLog.put(caller, new ArrayList<Call>());
		}
		List<Call> calls = customerCurrentCallLog.get(caller);
		calls.add(call);
	}
	
	public HashMap<String, List<Call>> getCustomersCallLog(){
		return customerCurrentCallLog;
	}
}
