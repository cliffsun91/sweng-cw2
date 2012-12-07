package com.acmetelecom.moneyformatters;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: oaj09
 * Date: 02/12/12
 * Time: 01:10
 * To change this template use File | Settings | File Templates.
 */
public interface MoneyFormatter {
    String penceToPounds(BigDecimal pence);
}
