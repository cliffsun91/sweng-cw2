package com.telecom.billingsystembuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.telecom.telephonecallbuilder.FinalTelephoneCallBuilder;

public class BillingSystemBuilder implements TelephoneCallsBuilder, FinalBillingSystemBuilder{

	List<FinalTelephoneCallBuilder> telephoneCallBuilderList;
	
	public BillingSystemBuilder() {
		telephoneCallBuilderList = new ArrayList<FinalTelephoneCallBuilder>();
	}
	
	@Override
	public FinalBillingSystemBuilder withTelephoneCalls(FinalTelephoneCallBuilder ... telephoneCallBuilders) throws CustomerNameMismatchException{
		telephoneCallBuilderList.addAll(Arrays.asList(telephoneCallBuilders));
		return this;
	}

	@Override
	public BillingSystem buildBillingSystem(List<Customer> customers) throws CustomerNameMismatchException {
		BillingSystem billingSystem = new BillingSystem();
		for (FinalTelephoneCallBuilder builder : telephoneCallBuilderList){
			billingSystem.callInitiated(builder.buildStartCall(customers));
			billingSystem.callCompleted(builder.buildEndCall(customers));
		}
		return billingSystem;
	}

}
