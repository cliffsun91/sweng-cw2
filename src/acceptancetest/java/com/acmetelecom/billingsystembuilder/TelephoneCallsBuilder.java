package com.acmetelecom.billingsystembuilder;

import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.exception.CustomerNameMismatchException;

public interface TelephoneCallsBuilder {

	public WithPrinterBuilder withTelephoneCalls(List<Call> calls) throws CustomerNameMismatchException;
}
