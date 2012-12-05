package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.acmetelecom.PeakOffPeakTime;
import com.acmetelecom.customer.Tariff;

public class DefaultCallCostCalculator implements CallCostCalculator {
	
	@Override
	public BigDecimal calculateCost(final PeakOffPeakTime peakOffPeakTime, final Tariff tariff) {
		BigDecimal peakCost = new BigDecimal(peakOffPeakTime.getPeakTime()).multiply(tariff.peakRate());
		BigDecimal offPeakCost = new BigDecimal(peakOffPeakTime.getOffPeakTime()).multiply(tariff.offPeakRate());
		return peakCost.add(offPeakCost).setScale(0, RoundingMode.HALF_UP);
	}
}
