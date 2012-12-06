package com.acmetelecom.printer;

import java.util.List;

import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;

public interface BillGenerator {

	void print(final Customer customer,
			final List<LineItem> calls, final String totalBill);
}
