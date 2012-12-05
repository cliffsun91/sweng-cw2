package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.TimeUtils.PeakOffPeakTime;
import com.acmetelecom.customer.Tariff;

public interface CallCostCalculator {

	BigDecimal calculateCost(final PeakOffPeakTime peakOffPeakTime,
			final Tariff tariff);

}