package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Tariff;

public interface TotalBillCalculator {

	BigDecimal calculateTotalBill(List<CallTime> calls, Tariff tariff, List<LineItem> items);
	
}
