package com.acmetelecom.MoneyFormatters;

import java.math.BigDecimal;

public class MoneyFormatter implements IMoneyFormatter {

    public String penceToPounds(BigDecimal pence) {
        BigDecimal pounds = pence.divide(new BigDecimal(100));
        return String.format("%.2f", pounds.doubleValue());
    }
}
