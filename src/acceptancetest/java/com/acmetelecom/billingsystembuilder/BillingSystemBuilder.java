package com.acmetelecom.billingsystembuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.BillingSystem;
import com.acmetelecom.call.Call;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.FileParseException;
import com.acmetelecom.timeutils.IPeakOffPeakPeriods;
import com.acmetelecom.timeutils.ITimeCalculator;
import com.acmetelecom.timeutils.PeakOffPeakPeriods;
import com.acmetelecom.timeutils.TimeCalculator;

public class BillingSystemBuilder implements TelephoneCallsBuilder, WithPrinterBuilder, FinalBillingSystemBuilder{

	List<Call> telephoneCalls;
	Printer printer;
	
	public BillingSystemBuilder() {
		telephoneCalls = new ArrayList<Call>();
	}
	
	@Override
	public WithPrinterBuilder withTelephoneCalls(List<Call> calls) throws CustomerNameMismatchException{
		telephoneCalls.addAll(calls);
		return this;
	}
	
	@Override
	public FinalBillingSystemBuilder withABillPrinter(Printer printer) {
		this.printer = printer;
		return this;
	}
	
	@Override
	public BillingSystem buildBillingSystem(List<Customer> customers) throws CustomerNameMismatchException, FileParseException {
		IPeakOffPeakPeriods period = PeakOffPeakPeriods.loadPeakOffPeakPeriods(new File("src/acceptancetest/java/com/acmetelecom/acceptancetest/timesAcceptance.xml"));
		ITimeCalculator calculator = new TimeCalculator(period);
		BillingSystem billingSystem = new BillingSystem(printer, calculator);
		for (Call call : telephoneCalls){
			billingSystem.fullCompletedCall(call);
		}
		return billingSystem;
	}


}
