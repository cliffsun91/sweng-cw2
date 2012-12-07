package com.acmetelecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.billcalculator.CustomerBillGenerator;
import com.acmetelecom.billcalculator.DefaultCustomerBillGenerator;
import com.acmetelecom.call.Call;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.printer.HtmlPrinter;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.TimeCalculator;
import com.acmetelecom.timeutils.DefaultTimeCalculator;

public class BillingSystem {

	private final HashMap<String, List<Call>> customerCurrentCallLog;
	private final CustomerBillGenerator customerBillGenerator;
	private final TariffStore tariffStore;
	private final List<Customer> customers;

	public BillingSystem() {
		this(HtmlPrinter.getInstance(), new DefaultTimeCalculator(), new DefaultTariffStore(), 
				CentralCustomerDatabase.getInstance().getCustomers());
	}

	public BillingSystem(Printer printer, TimeCalculator timeCalculator,
			TariffStore tariffStore, List<Customer> customers) {
		this(new DefaultCustomerBillGenerator(printer, timeCalculator), tariffStore, customers);
	}
	
	public BillingSystem(CustomerBillGenerator customerBillGenerator,
			TariffStore tariffStore, List<Customer> customers) {
		this.customerCurrentCallLog = new HashMap<String, List<Call>>();
		this.customerBillGenerator = customerBillGenerator;
		this.tariffStore = tariffStore;
		this.customers = customers;
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
	
	public void createCustomerBills() {		
		for (final Customer customer : this.customers) {
			final List<Call> calls = customerCurrentCallLog.get(customer.getPhoneNumber());			
			final Tariff tariff = tariffStore.getTariffFor(customer);
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

    //returns a deep clone of the hash map
	public HashMap<String, List<Call>> getCustomersCallLog(){
        HashMap<String, List<Call>> clone = new HashMap<String, List<Call>>();
		for (String s : customerCurrentCallLog.keySet()){
            List<Call>  clonedList = new ArrayList<Call>();
            for( Call c : customerCurrentCallLog.get(s)){
                clonedList.add(c.clone());
            }
            clone.put(s,clonedList);
        }
        return clone;
	}
}
