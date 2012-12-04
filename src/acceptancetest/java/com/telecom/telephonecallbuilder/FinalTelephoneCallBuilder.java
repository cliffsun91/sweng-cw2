package com.telecom.telephonecallbuilder;

import java.util.List;

import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;

public interface FinalTelephoneCallBuilder {

	public CallStart buildStartCall(List<Customer> customers) throws CustomerNameMismatchException;
	
	public CallEnd buildEndCall(List<Customer> customers) throws CustomerNameMismatchException;
}
