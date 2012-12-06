package com.acmetelecom.printer;

import java.util.List;

import com.acmetelecom.moneyformatters.IMoneyFormatter;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;

public class BillPrinter {

	private final IMoneyFormatter moneyFormatter;
	private final Printer printer;

	public BillPrinter(final IMoneyFormatter moneyFormatter, final Printer printer){
		this.moneyFormatter = moneyFormatter;
		this.printer = printer;
	}

	public void print(final Customer customer,
			final List<LineItem> calls, final String totalBill) {

		printer.printHeading(customer.getFullName(), customer.getPhoneNumber(),
				customer.getPricePlan());

		for (final LineItem call : calls) {
			printer.printItem(call.date(), call.callee(), call.durationMinutes(),
					moneyFormatter.penceToPounds(call.cost()));
		}

		printer.printTotal(totalBill);
	}

}
