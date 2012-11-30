package com.acmetelecom;

import java.util.List;

import com.acmetelecom.customer.Customer;

public class BillGenerator {

  public void send(final Customer customer,
      final List<BillingSystem.LineItem> calls, final String totalBill) {

    final Printer printer = HtmlPrinter.getInstance();
    printer.printHeading(customer.getFullName(), customer.getPhoneNumber(),
        customer.getPricePlan());
    for (final BillingSystem.LineItem call : calls) {
      printer.printItem(call.date(), call.callee(), call.durationMinutes(),
          MoneyFormatter.penceToPounds(call.cost()));
    }
    printer.printTotal(totalBill);
  }

}
