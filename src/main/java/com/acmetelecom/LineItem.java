package com.acmetelecom;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: oaj09
 * Date: 02/12/12
 * Time: 00:42
 * To change this template use File | Settings | File Templates.
 */
public interface LineItem {
    public String date();

    public String callee();

    public String durationMinutes();

    public BigDecimal cost();
}
