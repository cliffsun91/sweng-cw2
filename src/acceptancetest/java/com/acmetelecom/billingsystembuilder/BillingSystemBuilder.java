package com.acmetelecom.billingsystembuilder;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.call.CallTime;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.printer.Printer;

public class BillingSystemBuilder implements TelephoneCallsBuilder, WithPrinterBuilder, FinalBillingSystemBuilder{

	List<CallTime> telephoneCalls;
	Printer printer;
	
	public BillingSystemBuilder() {
		telephoneCalls = new ArrayList<CallTime>();
	}
	
	@Override
	public WithPrinterBuilder withTelephoneCalls(List<CallTime> calls) throws CustomerNameMismatchException{
		telephoneCalls.addAll(calls);
		return this;
	}
	
	@Override
	public FinalBillingSystemBuilder withABillPrinter(Printer printer) {
		this.printer = printer;
		return this;
	}
	
	@Override
	public BillingSystem buildBillingSystem(List<Customer> customers) throws CustomerNameMismatchException {
		BillingSystem billingSystem = new BillingSystem(printer);
		for (CallTime call : telephoneCalls){
			billingSystem.fullCompletedCall(call);
		}
		return billingSystem;
	}


}
