package com.acmetelecom.fixture;

import com.acmetelecom.assertion.AcmeTelecomTestAssertions;
import com.acmetelecom.assertion.CustomersBuilder;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.microtype.Person;
import com.acmetelecom.microtype.TariffPlan;
import com.acmetelecom.microtype.TelephoneNumber;
import com.telecom.billingsystembuilder.BillingSystemBuilder;
import com.telecom.billingsystembuilder.TelephoneCallsBuilder;
import com.telecom.telephonecallbuilder.DefaultTelephoneCallBuilder;
import com.telecom.telephonecallbuilder.FromCallerBuilder;

public class AcmeTelecomTestFixture {

	public CustomersBuilder givenAcmeTelecom(){
		return new AcmeTelecomTestAssertions();
	}
	
	public Customer aCustomer(Person person, TelephoneNumber number, TariffPlan tariffPlan){
		return new Customer(person.getName(), number.getTelephoneNumberAsString(), tariffPlan.getTariffCode());
	}
	
	
	public TelephoneCallsBuilder billingSystem(){
		return new BillingSystemBuilder();
	}
	
	public FromCallerBuilder aTelephoneCall(){
		FromCallerBuilder builder = new DefaultTelephoneCallBuilder();
		return builder;
	}
	
	public Person named(String name){
		return new Person(name);
	}
	
	public TelephoneNumber withTelephoneNumber(String number){
		return new TelephoneNumber(number);
	}
	
	public TariffPlan withTariffPlan(String tariffCode){
		return new TariffPlan(tariffCode);
	}
	

}
