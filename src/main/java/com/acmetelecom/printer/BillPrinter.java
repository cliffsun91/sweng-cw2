package com.acmetelecom.printer;

import java.util.List;

import com.acmetelecom.MoneyFormatters.IMoneyFormatter;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;

public class BillPrinter {

	private IMoneyFormatter moneyFormatter;

	public BillPrinter(IMoneyFormatter moneyFormatter){
		this.moneyFormatter = moneyFormatter;
	}

	public void print(final Customer customer,
			final List<LineItem> calls, final String totalBill,
			Printer printer) {

		printer.printHeading(customer.getFullName(), customer.getPhoneNumber(),
				customer.getPricePlan());

		for (final LineItem call : calls) {
			printer.printItem(call.date(), call.callee(), call.durationMinutes(),
					moneyFormatter.penceToPounds(call.cost()));
		}

		printer.printTotal(totalBill);
	}

}
