package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.billcalculator.CallCostCalculator;
import com.acmetelecom.billcalculator.LineItemFactory;
import com.acmetelecom.billcalculator.DefaultTotalBillCalculator;
import com.acmetelecom.call.Call;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.timeutils.TimeCalculator;
import com.acmetelecom.timeutils.PeakOffPeakTime;

@RunWith(JMock.class)
public class DefaultTotalBillCalculatorTest {

	private final Mockery context = new Mockery();
	private final LineItemFactory lineItemFactory = context.mock(LineItemFactory.class);
	private final CallCostCalculator callCostCalculator = context.mock(CallCostCalculator.class);
	private final TimeCalculator timeCalculator = context.mock(TimeCalculator.class);
	
    @Test
    public void testCalculateTotalBill() throws Exception {    	
        final LineItem lineItem = context.mock(LineItem.class);               
        final PeakOffPeakTime peakOffPeakTime = new PeakOffPeakTime(100, 100);
        final Tariff tariff = Tariff.Business;
        final BigDecimal callCost = new BigDecimal(10);
        
        final DateTime startTime = new DateTime();
        final DateTime endTime = startTime.plus(1800); 
        final Call call = new Call(startTime, "caller", "callee");
        call.setEndTime(endTime);
        List<Call> calls = new ArrayList<Call>();
        calls.add(call);
        
        context.checking(new Expectations(){{
        	oneOf(timeCalculator).calculateTimes(startTime, endTime); will(returnValue(peakOffPeakTime));
        	oneOf(callCostCalculator).calculateCost(peakOffPeakTime, tariff); will(returnValue(callCost));
        	oneOf(lineItemFactory).createCallLineItem(call, "callee", callCost, peakOffPeakTime); will(returnValue(lineItem));
        }});
    	
        List<LineItem> lineItems = new ArrayList<LineItem>();
    	new DefaultTotalBillCalculator(callCostCalculator, lineItemFactory, timeCalculator)
    		.calculateTotalBill(calls, tariff, lineItems);
    	
    	Assert.assertEquals(lineItem, lineItems.get(0));
    }
    
    @Test
    public void testCalculateTotalBillForNullCalls() throws Exception {    	
        final Tariff tariff = Tariff.Business; 
   	
        List<LineItem> lineItems = new ArrayList<LineItem>();
    	BigDecimal result = new DefaultTotalBillCalculator(callCostCalculator, lineItemFactory, timeCalculator)
    		.calculateTotalBill(null, tariff, lineItems);
    	
    	Assert.assertEquals(0, result.intValue());
    }
    
}
