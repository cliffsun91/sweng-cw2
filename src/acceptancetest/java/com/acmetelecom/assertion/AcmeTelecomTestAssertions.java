package com.acmetelecom.assertion;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.billingsystembuilder.FinalBillingSystemBuilder;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;

public class AcmeTelecomTestAssertions implements CustomersBuilder, BillingSystemBuilderBuilder, ExpectationBuilder, FinalAssertionChecker{

	List<Customer> customers;
	BillingSystem billingSystem;
	String expected;
	
	public AcmeTelecomTestAssertions() {
		customers = new ArrayList<Customer>();
	}
	
	@Override
	public BillingSystemBuilderBuilder hasCustomerDatabase(List<Customer> customerList){
		customers.addAll(customerList);
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
	public void assertResult(Printer printer ) {
		billingSystem.createCustomerBills(customers);
		String resultString = printer.getString();
		System.out.println(resultString);
		assertThat(resultString, equalTo(expected));
	}

	
}
