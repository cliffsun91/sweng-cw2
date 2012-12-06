package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.moneyformatters.IMoneyFormatter;
import com.acmetelecom.moneyformatters.MoneyFormatter;
import com.acmetelecom.printer.BillGenerator;
import com.acmetelecom.printer.DefaultBillGenerator;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.ITimeCalculator;

public class CustomerBillGenerator {
	
	private TotalBillCalculator totalBillCalculator;
	private BillGenerator billGenerator;
	private IMoneyFormatter moneyFormatter;

	public CustomerBillGenerator(Printer printer, ITimeCalculator timeCalculator) {
		this.totalBillCalculator = new DefaultTotalBillCalculator(timeCalculator);
		this.billGenerator = new DefaultBillGenerator(new MoneyFormatter(), printer);
		this.moneyFormatter = new MoneyFormatter();
	}
	
	public CustomerBillGenerator(TotalBillCalculator totalBillCalculator, 
			BillGenerator billGenerator, IMoneyFormatter moneyFormatter) {
		this.totalBillCalculator = totalBillCalculator;
		this.billGenerator = billGenerator;
		this.moneyFormatter = moneyFormatter;
	}
	
	public void createBillFor(final Customer customer, final List<CallTime> calls, Tariff tariff){
		final List<LineItem> items = new ArrayList<LineItem>();
		
		BigDecimal totalBill = totalBillCalculator.calculateTotalBill(calls, tariff, items);

		String totalBillString = this.moneyFormatter.penceToPounds(totalBill);

		billGenerator.print(customer, items, totalBillString);
	}
	
}
