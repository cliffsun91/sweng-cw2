package com.acmetelecom.acceptancetest;

import static com.acmetelecom.fixture.FixturePrinter.aStandardPrinter;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.fixture.AcmeTelecomTestFixture;
import com.acmetelecom.fixture.FixturePrinter;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.telephonecallbuilder.FinalTelephoneCallBuilder;
import com.acmetelecom.telephonecallbuilder.TelephoneCallRepresentation;

public class AcmeTelecomTest extends AcmeTelecomTestFixture{
	
	List<Customer> customerDatabase;
	
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
		
		List<TelephoneCallRepresentation> telephoneCalls 
									= createListOfTelephoneCalls(aTelephoneCall().fromCaller(named("James")).
																  				  toReceiver(named("Fred")).
																  				  withStartTime(telephoneCall1StartTime).
																  				  andWithEndTime(telephoneCall1EndTime).
																  				  endCall(customerDatabase)
																);
		
		//Random playing around
//		Tariff tarriff = Tariff.Standard;
//		
//		Customer customer = new Customer("","","");
//		
//		Tariff tarif1 = CentralTariffDatabase.getInstance().tarriffFor(customer);
		//finish random playing around
		
		givenAcmeTelecom().hasCustomerDatabase(customerDatabase).
			    		   andHasABillingSystem(
			    				   				billingSystem().withTelephoneCalls(telephoneCalls)
										  						//withABillPrinter(aStandardPrinter())
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
								   			      					      usingPrinter(aStandardPrinter())).
								   			      			    withNoCalls().
								   			      			      withBillTotal("0.00")
								   					    )
								   						      ).
						   assertResult();
						
	}
	
}
