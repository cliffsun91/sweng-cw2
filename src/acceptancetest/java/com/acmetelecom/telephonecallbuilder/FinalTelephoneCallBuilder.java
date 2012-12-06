package com.acmetelecom.telephonecallbuilder;

import java.util.List;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;

public interface FinalTelephoneCallBuilder {

	public CallTime endCall(List<Customer> customers) throws CustomerNameMismatchException;
}
