package com.acmetelecom;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public interface TariffStore {
	
	Tariff getTariffFor(Customer customer);
	
}
