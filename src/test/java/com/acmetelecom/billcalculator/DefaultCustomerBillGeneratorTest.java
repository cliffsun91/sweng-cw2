package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.moneyformatters.IMoneyFormatter;
import com.acmetelecom.printer.BillGenerator;

@RunWith(JMock.class)
public class DefaultCustomerBillGeneratorTest {

	final Mockery context = new Mockery();
	final TotalBillCalculator totalBillCalculator = context.mock(TotalBillCalculator.class);
	final BillGenerator billGenerator = context.mock(BillGenerator.class);
	final IMoneyFormatter moneyFormatter = context.mock(IMoneyFormatter.class);

	@Test
	public void testCreateBillForACustomer() throws Exception {    	
		final List<LineItem> items = new ArrayList<LineItem>();               
		final Tariff tariff = Tariff.Business;       
		final List<CallTime> calls = new ArrayList<CallTime>();
		final Customer customer = new Customer("Jo King", "12345678", "STANDARD");
		final BigDecimal totalBill = new BigDecimal(10);

		context.checking(new Expectations(){{
			oneOf(totalBillCalculator).calculateTotalBill(calls, tariff, items); will(returnValue(totalBill));
			oneOf(moneyFormatter).penceToPounds(totalBill); will(returnValue("total bill"));
			oneOf(billGenerator).print(customer, items, "total bill");
		}});

		new CustomerBillGenerator(totalBillCalculator, billGenerator, moneyFormatter)
			.createBillFor(customer, calls, tariff);
	}
}
