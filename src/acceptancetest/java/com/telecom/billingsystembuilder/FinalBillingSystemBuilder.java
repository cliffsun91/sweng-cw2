package com.telecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;

public interface FinalBillingSystemBuilder {
	
	public BillingSystem buildBillingSystem(List<Customer> customers) throws CustomerNameMismatchException;
}
