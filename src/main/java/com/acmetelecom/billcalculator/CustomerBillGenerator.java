package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.moneyformatters.MoneyFormatter;
import com.acmetelecom.printer.BillGenerator;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.ITimeCalculator;

public class CustomerBillGenerator {
	
	private TotalBillCalculator totalBillCalculator;
	private BillGenerator billGenerator;

	public CustomerBillGenerator(Printer printer, ITimeCalculator timeCalculator) {
		this.totalBillCalculator = new TotalBillCalculator(timeCalculator);
		this.billGenerator = new BillGenerator(new MoneyFormatter(), printer);
	}

	public void createBillFor(final Customer customer, final List<CallTime> calls, Tariff tariff){
		final List<LineItem> items = new ArrayList<LineItem>();
		
		BigDecimal totalBill = totalBillCalculator.calculateTotalBill(calls, tariff, items);

		String totalBillString = new MoneyFormatter().penceToPounds(totalBill);

		billGenerator.print(customer, items, totalBillString);
	}
	
}
