package com.acmetelecom.billingsystembuilder;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.fixture.FixturePrinter;
import com.acmetelecom.telephonecallbuilder.FinalTelephoneCallBuilder;
import com.acmetelecom.telephonecallbuilder.TelephoneCallRepresentation;

public class BillingSystemBuilder implements TelephoneCallsBuilder, FinalBillingSystemBuilder{

	List<TelephoneCallRepresentation> telephoneCalls;
	
	public BillingSystemBuilder() {
		telephoneCalls = new ArrayList<TelephoneCallRepresentation>();
	}
	
	@Override
	public FinalBillingSystemBuilder withTelephoneCalls(List<TelephoneCallRepresentation> calls) throws CustomerNameMismatchException{
		telephoneCalls.addAll(calls);
		return this;
	}
	
	@Override
	public BillingSystem buildBillingSystem(List<Customer> customers) throws CustomerNameMismatchException {
		BillingSystem billingSystem = new BillingSystem(new FixturePrinter());
		for (TelephoneCallRepresentation call : telephoneCalls){
			billingSystem.callInitiated(call.getStartCall());
			billingSystem.callCompleted(call.getEndCall());
		}
		return billingSystem;
	}

}
