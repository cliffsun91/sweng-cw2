package com.telecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.exception.CustomerNameMismatchException;
import com.telecom.telephonecallbuilder.FinalTelephoneCallBuilder;

public interface TelephoneCallsBuilder {

	public FinalBillingSystemBuilder withTelephoneCalls(List<FinalTelephoneCallBuilder> telephoneCallBuilders) throws CustomerNameMismatchException;
}
