package com.telecom.billingsystembuilder;

import com.acmetelecom.exception.CustomerNameMismatchException;
import com.telecom.telephonecallbuilder.FinalTelephoneCallBuilder;

public interface TelephoneCallsBuilder {

	public FinalBillingSystemBuilder withTelephoneCalls(FinalTelephoneCallBuilder ... telephoneCallBuilder) throws CustomerNameMismatchException;
}
