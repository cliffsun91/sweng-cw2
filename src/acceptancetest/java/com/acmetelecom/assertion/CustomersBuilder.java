package com.acmetelecom.assertion;

import com.acmetelecom.customer.Customer;

public interface CustomersBuilder {

	public BillingSystemBuilderBuilder hasCustomers(Customer ... customerList);
}
