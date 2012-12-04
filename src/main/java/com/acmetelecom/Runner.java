package com.acmetelecom;

import static com.acmetelecom.callevent.CallEnd.endCall;
import static com.acmetelecom.callevent.CallStart.startCall;

import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.customer.CentralCustomerDatabase;
import com.acmetelecom.customer.Customer;

public class Runner {

  public static void main(final String[] args) throws Exception {

	DateTime timeNow = DateTime.now();
	DateTime newTime;
	
    System.out.println("Running...");
    final BillingSystem billingSystem = new BillingSystem(HtmlPrinter.getInstance());
    billingSystem.callInitiated(startCall("447722113434", "447766511332", timeNow));
    billingSystem.callCompleted(endCall("447722113434", "447766511332", newTime = timeNow.plusSeconds(20)));
    billingSystem.callInitiated(startCall("447722113434", "447711111111", newTime = newTime.plusSeconds(10)));
    billingSystem.callCompleted(endCall("447722113434", "447711111111", newTime = newTime.plusSeconds(30)));
    billingSystem.callInitiated(startCall("447777765432", "447711111111", newTime = newTime.plusSeconds(25)));
    billingSystem.callCompleted(endCall("447777765432", "447711111111", newTime = newTime.plusSeconds(15)));
    
    final List<Customer> customers = CentralCustomerDatabase.getInstance().getCustomers();
    
    billingSystem.createCustomerBills(customers);
  }


}
