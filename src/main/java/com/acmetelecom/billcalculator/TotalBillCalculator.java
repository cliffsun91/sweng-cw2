package com.acmetelecom.billcalculator;

import java.math.BigDecimal;
import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.call.LineItem;
import com.acmetelecom.customer.Tariff;

public interface TotalBillCalculator {

	BigDecimal calculateTotalBill(List<Call> calls, Tariff tariff, List<LineItem> items);
	
}
