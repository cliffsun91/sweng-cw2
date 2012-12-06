package com.acmetelecom.printbillbuilder;

import org.joda.time.DateTime;

import com.acmetelecom.customer.Customer;

public interface AddCallsToBillBuilder extends PrintEndBillBuilder{
	public AddCallsToBillBuilder withACall(DateTime startTime, DateTime endTime, Customer receiver, String cost);
	
	public PrintEndBillBuilder withNoCalls();
	
}
