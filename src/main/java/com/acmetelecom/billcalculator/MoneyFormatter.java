package com.acmetelecom.billcalculator;

import java.math.BigDecimal;

import com.acmetelecom.IMoneyFormatter;

class MoneyFormatter implements IMoneyFormatter {

    public String penceToPounds(BigDecimal pence) {
        BigDecimal pounds = pence.divide(new BigDecimal(100));
        return String.format("%.2f", pounds.doubleValue());
    }
}
