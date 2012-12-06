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

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.timeutils.ITimeCalculator;
import com.acmetelecom.timeutils.PeakOffPeakTime;

@RunWith(JMock.class)
public class TotalBillCalculatorTest {

    final Mockery context = new Mockery();
    final LineItemFactory lineItemFactory = context.mock(LineItemFactory.class);
    final CallCostCalculator callCostCalculator = context.mock(CallCostCalculator.class);
    final ITimeCalculator timeCalculator = context.mock(ITimeCalculator.class);
	
    @Test
    public void testCalculateTotalBill() throws Exception {    	
        final LineItem lineItem = context.mock(LineItem.class);               
        final PeakOffPeakTime peakOffPeakTime = new PeakOffPeakTime(100, 100);
        final Tariff tariff = Tariff.Business;
        final BigDecimal callCost = new BigDecimal(10);
        
        final DateTime startTime = new DateTime();
        final DateTime endTime = startTime.plus(1800); 
        final CallTime callTime = new CallTime(startTime, "caller", "callee");
        callTime.setEndTime(endTime);
        List<CallTime> calls = new ArrayList<CallTime>();
        calls.add(callTime);
        
        context.checking(new Expectations(){{
        	oneOf(timeCalculator).calculateTimes(startTime, endTime); will(returnValue(peakOffPeakTime));
        	oneOf(callCostCalculator).calculateCost(peakOffPeakTime, tariff); will(returnValue(callCost));
        	oneOf(lineItemFactory).createCallTimeLineItem(callTime, "callee", callCost, peakOffPeakTime); will(returnValue(lineItem));
        }});
    	
        List<LineItem> lineItems = new ArrayList<LineItem>();
    	new DefaultTotalBillCalculator(callCostCalculator, lineItemFactory, timeCalculator)
    		.calculateTotalBill(calls, tariff, lineItems);
    	
    	Assert.assertEquals(lineItem, lineItems.get(0));
    }
}
