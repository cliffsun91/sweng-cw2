package com.acmetelecom.call;

import java.math.BigDecimal;

import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

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
    	//TODO: Make a single instance of this somewhere
		PeriodFormatter minutesAndSeconds = new PeriodFormatterBuilder()
											.appendMinutes()
											.appendSecondsWithMillis()
											.toFormatter();
        return minutesAndSeconds.print(call.duration().toPeriod());
    }

    public BigDecimal cost() {
        return callCost;
    }
}
