package com.acmetelecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.telephonecallbuilder.FinalTelephoneCallBuilder;
import com.acmetelecom.telephonecallbuilder.TelephoneCallRepresentation;

public interface TelephoneCallsBuilder {

	public FinalBillingSystemBuilder withTelephoneCalls(List<TelephoneCallRepresentation> calls) throws CustomerNameMismatchException;
}
