package com.acmetelecom.acceptancetest;

import static com.acmetelecom.fixture.FixturePrinter.aStandardPrinter;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.call.CallTime;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.fixture.AcmeTelecomTestFixture;
import com.acmetelecom.printer.Printer;

public class AcmeTelecomTest extends AcmeTelecomTestFixture{
	
	List<Customer> customerDatabase;
	List<CallTime> telephoneCalls;
	
	@Before
	public void Setup(){
		customerDatabase = createNewDatabaseWithCustomers(aCustomer(named("James"), 
					   												withTelephoneNumber("+447567891234"), 
					   												withTariffPlan("Standard")),
					   									  aCustomer(named("Fred"), 
					   												withTelephoneNumber("+447912345678"), 
					   												withTariffPlan("Standard")));
	}
	
	@Test
	public void TestBillingSystemWithOneCallInOffPeakPeriodPrintsBillWithOffPeakChargeOnly() throws CustomerNameMismatchException{

		DateTime telephoneCall1StartTime = timeAndDate(year(2012), month(1), dayOfMonth(1), hour(5), minute(0));
		DateTime telephoneCall1EndTime = timeAndDate(year(2012), month(1), dayOfMonth(1), hour(6), minute(0));
		
		 
		telephoneCalls = createListOfTelephoneCalls(aTelephoneCall().fromCaller(named("James")).
																  	 toReceiver(named("Fred")).
																  	 withStartTime(telephoneCall1StartTime).
																  	 andWithEndTime(telephoneCall1EndTime).
																  	 endCall(customerDatabase)
												   );
		
//		Tariff tarif1 = CentralTariffDatabase.getInstance().tarriffFor(customer);
		Printer billPrinter = aStandardPrinter();
		
		givenAcmeTelecom().hasCustomerDatabase(customerDatabase).
			    		   andHasABillingSystem(
			    				   				billingSystem().withTelephoneCalls(telephoneCalls).
										  						withABillPrinter(aStandardPrinter())
								          	   ).
						   weExpectTheFollowingBillToBePrinted(
								   						aBill(forCustomer(customer(named("James"), customerDatabase), 
								   								          usingPrinter(aStandardPrinter())).
								   								withACall(startTime(telephoneCall1StartTime),
								   										  endTime(telephoneCall1EndTime),
								   									  	  toReceiver(customer(named("Fred"), customerDatabase)),
								   									  	  withCost("7.20")).
								   							      withBillTotal("7.20"),
								   			      			  forCustomer(customer(named("Fred"), customerDatabase), 
								   			      					      usingPrinter(billPrinter)).
								   			      			    withNoCalls().
								   			      			      withBillTotal("0.00")
								   					    )
								   						      ).
						   assertResult(billPrinter);
	}
	
	@Test
	public void TestBillingSystemWithCallWithOverlapPeakPeriodsPrintsBillWhichChargesTheCorrectAmount() throws CustomerNameMismatchException{

		DateTime telephoneCall1StartTime = timeAndDate(year(2012), month(1), dayOfMonth(1), hour(7), minute(30));
		DateTime telephoneCall1EndTime = timeAndDate(year(2012), month(1), dayOfMonth(1), hour(8), minute(30));
		
		telephoneCalls = createListOfTelephoneCalls(aTelephoneCall().fromCaller(named("Fred")).
																  	 toReceiver(named("James")).
																     withStartTime(telephoneCall1StartTime).
															    	 andWithEndTime(telephoneCall1EndTime).
															    	 endCall(customerDatabase)
																);
		
//		Tariff tarif1 = CentralTariffDatabase.getInstance().tarriffFor(customer);
		Printer billPrinter = aStandardPrinter();
		
		givenAcmeTelecom().hasCustomerDatabase(customerDatabase).
			    		   andHasABillingSystem(
			    				   				billingSystem().withTelephoneCalls(telephoneCalls).
										  						withABillPrinter(aStandardPrinter())
								          	   ).
						   weExpectTheFollowingBillToBePrinted(
								   						aBill(forCustomer(customer(named("James"), customerDatabase), 
								   								          usingPrinter(billPrinter)).
								   							    withNoCalls().
								   								  withBillTotal("0.00"),
								   			      			  forCustomer(customer(named("Fred"), customerDatabase), 
								   			      					      usingPrinter(aStandardPrinter())).
								   			      			    withACall(startTime(telephoneCall1StartTime),
									   									  endTime(telephoneCall1EndTime),
									   									  toReceiver(customer(named("James"), customerDatabase)),
									   									  withCost("12.60")).
									   							  withBillTotal("12.60")
								   					    )
								   						      ).
						   assertResult(billPrinter);
	}
	
}
