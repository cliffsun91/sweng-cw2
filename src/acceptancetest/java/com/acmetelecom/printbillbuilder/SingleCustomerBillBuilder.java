package com.acmetelecom.printbillbuilder;

import org.joda.time.DateTime;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.TimeFormatter;

public class SingleCustomerBillBuilder implements AddCallsToBillBuilder{
	
	Printer printer;
	String bill;
	
	public SingleCustomerBillBuilder(Customer customer, Printer printer) {
		this.printer = printer;
		printer.printHeading(customer.getFullName(), customer.getPhoneNumber(), customer.getPricePlan());
	}

	@Override
	public AddCallsToBillBuilder withACall(DateTime startTime, DateTime endTime, Customer receiver, String cost) {
		String start = TimeFormatter.formatDateTime(startTime);
		long seconds = (endTime.getMillis() - startTime.getMillis())/1000;
		String duration = TimeFormatter.formatMinutesFromSeconds(seconds);
		printer.printItem(start, receiver.getPhoneNumber(), duration, cost);
		return this;
	}

	@Override
	public PrintEndBillBuilder withNoCalls() {
		return this;
	}
	
	@Override
	public String withBillTotal(String total) {
		printer.printTotal(total);
		return printer.getString();
	}

	
}
