package com.acmetelecom.acceptancetest;

import static com.acmetelecom.fixture.FixturePrinter.aStandardPrinter;

import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.fixture.AcmeTelecomTestFixture;
import com.acmetelecom.fixture.FixturePrinter;
import com.telecom.telephonecallbuilder.FinalTelephoneCallBuilder;

public class AcmeTelecomTest extends AcmeTelecomTestFixture{

	@Test
	public void TestBillingSystemWithOneCallInOffPeakPeriodPrintsBillWithOffPeakChargeOnly() throws CustomerNameMismatchException{
		// a telephone call from caller named James with telephone no. +447567891234 
		// to a receiver named Fred with telephone no. +447912345678
		// with a start time of 5:00:00am and end time of 5:00:30am  - 30 seconds

		//Billing System shouldn't create customer list, it should be passed in
		//so now it needs to be extracted out.
		
		List<Customer> customerDatabase = createNewDatabaseWithCustomers(aCustomer(named("James"), 
	   			  																   withTelephoneNumber("+447567891234"), 
	   			  																   withTariffPlan("Standard")),
																	     aCustomer(named("Fred"), 
																	    		   withTelephoneNumber("+447912345678"), 
																			       withTariffPlan("Standard")));

		DateTime telephoneCall1StartTime = timeAndDate(year(2012), month(1), dayOfMonth(1), hour(5), minute(0));
		DateTime telephoneCall1EndTime = timeAndDate(year(2012), month(1), dayOfMonth(1), hour(6), minute(0));
		
		List<FinalTelephoneCallBuilder> telephoneCalls 
					= createListOfTelephoneCalls(aTelephoneCall().fromCaller(named("James")).
																  toReceiver(named("Fred")).
																  withStartTime(telephoneCall1StartTime).
																  andWithEndTime(telephoneCall1EndTime)
												);
		
		//String expected = generateBillUsingPrinter(customerDatabase, telephoneCalls, aStandardPrinter());
				
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
						   weExpectTheFollowingBillToBePrinted("boo").assertResult();
						
	}
	
}
