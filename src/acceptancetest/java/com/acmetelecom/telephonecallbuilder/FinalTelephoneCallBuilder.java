package com.acmetelecom.telephonecallbuilder;

import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;

public interface FinalTelephoneCallBuilder {

	public Call endCall(List<Customer> customers) throws CustomerNameMismatchException;
}
