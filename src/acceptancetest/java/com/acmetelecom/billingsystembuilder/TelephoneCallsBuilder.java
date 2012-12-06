package com.acmetelecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.exception.CustomerNameMismatchException;

public interface TelephoneCallsBuilder {

	public WithPrinterBuilder withTelephoneCalls(List<CallTime> calls) throws CustomerNameMismatchException;
}
