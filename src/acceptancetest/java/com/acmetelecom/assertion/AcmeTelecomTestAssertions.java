package com.acmetelecom.assertion;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.telecom.billingsystembuilder.FinalBillingSystemBuilder;

public class AcmeTelecomTestAssertions implements CustomersBuilder, BillingSystemBuilderBuilder, ExpectationBuilder, FinalAssertionChecker{

	List<Customer> customers;
	BillingSystem billingSystem;
	String expected;
	
	public AcmeTelecomTestAssertions() {
		customers = new ArrayList<Customer>();
	}
	
	
	@Override
	public BillingSystemBuilderBuilder hasCustomers(Customer ... customerList){
		for (Customer c : customerList){
			customers.add(c);	
		}
		return this;
	}

	@Override
	public ExpectationBuilder andHasABillingSystem(FinalBillingSystemBuilder builder) throws CustomerNameMismatchException {
		billingSystem = builder.buildBillingSystem(customers);
		return this;
	}

	@Override
	public FinalAssertionChecker weExpectTheFollowingBillToBePrinted(String expected) {
		this.expected = expected;
		return this;
	}

	@Override
	public void assertResult() {
		billingSystem.createCustomerBills(customers);
	}

	
}
