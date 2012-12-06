package com.acmetelecom.billingsystembuilder;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.telephonecallbuilder.TelephoneCallRepresentation;

public class BillingSystemBuilder implements TelephoneCallsBuilder, WithPrinterBuilder, FinalBillingSystemBuilder{

	List<TelephoneCallRepresentation> telephoneCalls;
	Printer printer;
	
	public BillingSystemBuilder() {
		telephoneCalls = new ArrayList<TelephoneCallRepresentation>();
	}
	
	@Override
	public WithPrinterBuilder withTelephoneCalls(List<TelephoneCallRepresentation> calls) throws CustomerNameMismatchException{
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
		for (TelephoneCallRepresentation call : telephoneCalls){
			billingSystem.callInitiated(call.getStartCall());
			billingSystem.callCompleted(call.getEndCall());
		}
		return billingSystem;
	}


}
