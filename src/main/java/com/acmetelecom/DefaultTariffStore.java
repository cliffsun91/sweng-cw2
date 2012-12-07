package com.acmetelecom;

import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public class DefaultTariffStore implements TariffStore {

	@Override
	public Tariff getTariffFor(Customer customer) {
		return CentralTariffDatabase.getInstance().tarriffFor(customer);
	}

}
