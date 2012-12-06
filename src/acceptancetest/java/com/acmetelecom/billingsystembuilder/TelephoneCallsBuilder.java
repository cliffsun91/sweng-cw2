package com.acmetelecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.telephonecallbuilder.TelephoneCallRepresentation;

public interface TelephoneCallsBuilder {

	public WithPrinterBuilder withTelephoneCalls(List<TelephoneCallRepresentation> calls) throws CustomerNameMismatchException;
}
