package com.acmetelecom.assertion;

import java.util.List;

import com.acmetelecom.customer.Customer;

public interface CustomersBuilder {

	public BillingSystemBuilderBuilder hasCustomerDatabase(List<Customer> customerList);
}
