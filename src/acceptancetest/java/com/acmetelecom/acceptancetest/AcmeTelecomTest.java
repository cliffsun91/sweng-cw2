package com.acmetelecom.acceptancetest;

import org.junit.Test;

import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.fixture.AcmeTelecomTestFixture;

public class AcmeTelecomTest extends AcmeTelecomTestFixture{

	@Test
	public void TestBillingSystemWithOneCallInOffPeakPeriodPrintsBillWithOffPeakChargeOnly() throws CustomerNameMismatchException{
		// a telephone call from caller named James with telephone no. +447567891234 
		// to a receiver named Fred with telephone no. +447912345678
		// with a start time of 5:00:00am and end time of 5:00:30am  - 30 seconds

		//Billing System shouldn't create customer list, it should be passed in
		//so now it needs to be extracted out.
		
		givenAcmeTelecom().hasCustomers(
									    aCustomer(named("James"), 
			    			 		   			  withTelephoneNumber("+447567891234"), 
			    			 		   			  withTariffPlan("Standard")),
			    			 		    aCustomer(named("Fred"), 
			    			 		    		  withTelephoneNumber("+447912345678"), 
			    			 				      withTariffPlan("Standard"))
			    			 		   ).
			    		   andHasABillingSystem(billingSystem().withTelephoneCalls(
										  								aTelephoneCall().fromCaller(named("James")).
										  								toReceiver(named("Fred")).
										  								withStartTime(5).
										  								andWithEndTime(6))
								          ).
						   weExpectTheFollowingBillToBePrinted("boo").assertResult();
						
	}
	
}
