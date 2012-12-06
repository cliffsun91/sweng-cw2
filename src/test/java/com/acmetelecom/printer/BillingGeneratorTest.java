package com.acmetelecom.printer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.moneyformatters.IMoneyFormatter;


@RunWith(JMock.class)
public class BillingGeneratorTest {

    final Mockery context = new Mockery();
    final Customer customer = new Customer("Jo King","12345678","xxx");
    final Printer printer = context.mock(Printer.class);
    final IMoneyFormatter moneyFormatter = context.mock(IMoneyFormatter.class);

    @Test
    public void testPrinter() throws Exception {
        final LineItem firstCall = context.mock(LineItem.class);
        final BigDecimal decimal = new BigDecimal(50);

        context.checking(new Expectations(){{
            oneOf(printer).printHeading("Jo King","12345678","xxx");

            oneOf(firstCall).date(); will(returnValue("Today"));
            oneOf(firstCall).callee(); will(returnValue("87654321"));
            oneOf(firstCall).durationMinutes(); will(returnValue("20"));
            oneOf(firstCall).cost(); will(returnValue(decimal));
            oneOf(moneyFormatter).penceToPounds(decimal); will(returnValue("50"));
            oneOf(printer).printItem("Today", "87654321", "20", "50");

            oneOf(printer).printTotal("50");
            
        }});

        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(firstCall);
        new DefaultBillGenerator(moneyFormatter, printer).print(customer, lineItems, "50");
    }
}
