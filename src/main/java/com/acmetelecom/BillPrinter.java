package com.acmetelecom;

import java.util.List;

import com.acmetelecom.customer.Customer;

public class BillPrinter {

	private IMoneyFormatter moneyFormatter;

	BillPrinter(IMoneyFormatter moneyFormatter){
		this.moneyFormatter = moneyFormatter;
	}

	public void print(final Customer customer,
			final List<LineItem> calls, final String totalBill,
			Printer printer) {

		printer.createHeading(customer.getFullName(), customer.getPhoneNumber(),
				customer.getPricePlan());

		for (final LineItem call : calls) {
			printer.createItem(call.date(), call.callee(), call.durationMinutes(),
					moneyFormatter.penceToPounds(call.cost()));
		}

		printer.createTotal(totalBill);
		
		printer.printAllToConsole();
	}

}
