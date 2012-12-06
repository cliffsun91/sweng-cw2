package com.acmetelecom.assertion;

import com.acmetelecom.billingsystembuilder.FinalBillingSystemBuilder;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.timeutils.FileParseException;

public interface BillingSystemBuilderBuilder {

	public ExpectationBuilder andHasABillingSystem(FinalBillingSystemBuilder builder) throws CustomerNameMismatchException, FileParseException;
}
