package com.acmetelecom.assertion;

import com.acmetelecom.exception.CustomerNameMismatchException;
import com.telecom.billingsystembuilder.FinalBillingSystemBuilder;

public interface BillingSystemBuilderBuilder {

	public ExpectationBuilder andHasABillingSystem(FinalBillingSystemBuilder builder) throws CustomerNameMismatchException;
}
