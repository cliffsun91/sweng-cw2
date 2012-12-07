package com.acmetelecom.billcalculator;

import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public interface CustomerBillGenerator {

	void createBillFor(final Customer customer, final List<Call> calls, Tariff tariff);
	
}
