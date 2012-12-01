package com.acmetelecom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.acmetelecom.call.Call;
import com.acmetelecom.call.Call;
import com.acmetelecom.callevent.AbstractCallEvent;
import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallEvent;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.CentralTariffDatabase;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

public class BillingSystem {

  private final List<AbstractCallEvent> callLog = new ArrayList<AbstractCallEvent>();

  public void callInitiated(final String caller, final String callee) {
    callLog.add(new CallStart(caller, callee));
  }

  public void callCompleted(final String caller, final String callee) {
    callLog.add(new CallEnd(caller, callee));
  }

  public void createCustomerBills() {
    final List<Customer> customers = CentralCustomerDatabase.getInstance()
        .getCustomers();
    for (final Customer customer : customers) {
      createBillFor(customer);
    }
    callLog.clear();
  }

  private void createBillFor(final Customer customer) {
    final List<AbstractCallEvent> customerEvents = new ArrayList<AbstractCallEvent>();
    for (final AbstractCallEvent callEvent : callLog) {
      if (callEvent.getCaller().equals(customer.getPhoneNumber())) {
        customerEvents.add(callEvent);
      }
    }

    final List<Call> calls = new ArrayList<Call>();

    CallEvent start = null;
    for (final CallEvent event : customerEvents) {
      if (event instanceof CallStart) {
        start = event;
      }
      if (event instanceof CallEnd && start != null) {
        calls.add(new Call(start, event));
        start = null;
      }
    }

    BigDecimal totalBill = new BigDecimal(0);
    final List<LineItem> items = new ArrayList<LineItem>();

    for (final Call call : calls) {

      final Tariff tariff = CentralTariffDatabase.getInstance().tarriffFor(
          customer);

      BigDecimal cost;

      final DaytimePeakPeriod peakPeriod = new DaytimePeakPeriod();
      cost = calculateCost(call, peakPeriod, tariff);

      cost = cost.setScale(0, RoundingMode.HALF_UP);
      final BigDecimal callCost = cost;
      totalBill = totalBill.add(callCost);
      items.add(new LineItem(call, callCost));
    }

    new BillGenerator().send(customer, items,
        MoneyFormatter.penceToPounds(totalBill));
  }

  private BigDecimal calculateCost(final Call call,
      final DaytimePeakPeriod peakPeriod, final Tariff tariff) {

    /*
     * NEW FUNCTIONALITY - let's look at old functionality first... // Only
     * charge peak rate during peak times final int durationOffPeak =
     * peakPeriod.calcOffPeakTime(call.startTime(), call.endTime(),
     * call.durationSeconds()); final int durationPeak =
     * peakPeriod.calcPeakTime(call.startTime(), call.endTime(),
     * call.durationSeconds());
     * 
     * return new
     * BigDecimal(durationOffPeak).multiply(tariff.offPeakRate()).add( new
     * BigDecimal(durationPeak).multiply(tariff.peakRate()));
     */

    if (peakPeriod.offPeak(call.startTime())
        && peakPeriod.offPeak(call.endTime())
        && call.durationSeconds() < 12 * 60 * 60) {
      return new BigDecimal(call.durationSeconds()).multiply(tariff
          .offPeakRate());
    } else {
      return new BigDecimal(call.durationSeconds()).multiply(tariff.peakRate());
    }

  }

  static class LineItem {
    private final Call call;
    private final BigDecimal callCost;

    public LineItem(final Call call, final BigDecimal callCost) {
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
}
