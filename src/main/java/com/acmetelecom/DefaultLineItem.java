package com.acmetelecom;

import com.acmetelecom.call.Call;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: oaj09
 * Date: 02/12/12
 * Time: 00:43
 * To change this template use File | Settings | File Templates.
 */
public class DefaultLineItem implements LineItem {
    private final Call call;
    private final BigDecimal callCost;

    public DefaultLineItem(final Call call, final BigDecimal callCost) {
        this.call = call;
        this.callCost = callCost;
    }

    public String date() {
        return call.date();
    }

    public String callee() {
        return call.callee();
    }

    public String durationMinutes() {
        return "" + call.durationSeconds() / 60 + ":"
                + String.format("%02d", call.durationSeconds() % 60);
    }

    public BigDecimal cost() {
        return callCost;
    }
}
