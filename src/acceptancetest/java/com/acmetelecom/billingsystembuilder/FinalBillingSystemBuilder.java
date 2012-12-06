package com.acmetelecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.timeutils.FileParseException;

public interface FinalBillingSystemBuilder {
	
	public BillingSystem buildBillingSystem(List<Customer> customers) throws CustomerNameMismatchException, FileParseException;
}
