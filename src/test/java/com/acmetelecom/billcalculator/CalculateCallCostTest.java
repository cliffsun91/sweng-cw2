package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.acmetelecom.billcalculator.DefaultCallCostCalculator;
import com.acmetelecom.customer.Tariff;
import com.acmetelecom.timeutils.PeakOffPeakTime;

public class CalculateCallCostTest {

    @Test
    public void testCallCostWithLeisureTariff() throws Exception {
    	PeakOffPeakTime peakOffPeakTime = new PeakOffPeakTime(200, 50);
    	Tariff tariff = Tariff.Leisure;
    	BigDecimal cost = new DefaultCallCostCalculator().calculateCost(peakOffPeakTime, tariff);
    	Assert.assertEquals(cost.longValue(), 165);
    }
    
    @Test
    public void testCallCostWithBusinessTariff() throws Exception {
    	PeakOffPeakTime peakOffPeakTime = new PeakOffPeakTime(300, 600);
    	Tariff tariff = Tariff.Business;
    	BigDecimal cost = new DefaultCallCostCalculator().calculateCost(peakOffPeakTime, tariff);
    	Assert.assertEquals(cost.longValue(), 270);
    }
    
    @Test
    public void testCallCostWithStandardTariff() throws Exception {
    	PeakOffPeakTime peakOffPeakTime = new PeakOffPeakTime(100, 200);
    	Tariff tariff = Tariff.Standard;
    	BigDecimal cost = new DefaultCallCostCalculator().calculateCost(peakOffPeakTime, tariff);
    	Assert.assertEquals(cost.longValue(), 90);
    }	
}
