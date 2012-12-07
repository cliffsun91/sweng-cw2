package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.moneyformatters.MoneyFormatter;
import com.acmetelecom.moneyformatters.DefaultMoneyFormatter;
import com.acmetelecom.printer.BillGenerator;
import com.acmetelecom.printer.DefaultBillGenerator;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.timeutils.TimeCalculator;

public class DefaultCustomerBillGenerator implements CustomerBillGenerator {
	
	private TotalBillCalculator totalBillCalculator;
	private BillGenerator billGenerator;
	private MoneyFormatter moneyFormatter;

	public DefaultCustomerBillGenerator(Printer printer, TimeCalculator timeCalculator) {
		this.totalBillCalculator = new DefaultTotalBillCalculator(timeCalculator);
		this.billGenerator = new DefaultBillGenerator(new DefaultMoneyFormatter(), printer);
		this.moneyFormatter = new DefaultMoneyFormatter();
	}
	
	public DefaultCustomerBillGenerator(TotalBillCalculator totalBillCalculator, 
			BillGenerator billGenerator, MoneyFormatter moneyFormatter) {
		this.totalBillCalculator = totalBillCalculator;
		this.billGenerator = billGenerator;
		this.moneyFormatter = moneyFormatter;
	}
	
	public void createBillFor(final Customer customer, final List<Call> calls, Tariff tariff){
		final List<LineItem> items = new ArrayList<LineItem>();
		
		BigDecimal totalBill = totalBillCalculator.calculateTotalBill(calls, tariff, items);
		String totalBillString = this.moneyFormatter.penceToPounds(totalBill);
		billGenerator.print(customer, items, totalBillString);
	}
	
}
