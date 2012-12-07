package com.acmetelecom.moneyformatters;

import java.math.BigDecimal;

public class DefaultMoneyFormatter implements MoneyFormatter {

    public String penceToPounds(BigDecimal pence) {
        BigDecimal pounds = pence.divide(new BigDecimal(100));
        return String.format("%.2f", pounds.doubleValue());
    }
}
