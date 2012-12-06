package com.acmetelecom.fixture;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.assertion.AcmeTelecomTestAssertions;
import com.acmetelecom.assertion.CustomersBuilder;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.microtype.Person;
import com.acmetelecom.microtype.TariffPlan;
import com.acmetelecom.microtype.TelephoneNumber;
import com.acmetelecom.printer.Printer;
import com.telecom.billingsystembuilder.BillingSystemBuilder;
import com.telecom.billingsystembuilder.TelephoneCallsBuilder;
import com.telecom.telephonecallbuilder.DefaultTelephoneCallBuilder;
import com.telecom.telephonecallbuilder.FinalTelephoneCallBuilder;
import com.telecom.telephonecallbuilder.FromCallerBuilder;
import com.telecom.telephonecallbuilder.TelephoneCallRepresentation;

public class AcmeTelecomTestFixture {

	public CustomersBuilder givenAcmeTelecom(){
		return new AcmeTelecomTestAssertions();
	}
	
	public Customer aCustomer(Person person, TelephoneNumber number, TariffPlan tariffPlan){
		return new Customer(person.getName(), number.getTelephoneNumberAsString(), tariffPlan.getTariffCode());
	}
	
	public List<Customer> createNewDatabaseWithCustomers(Customer ... customers) {
		return Arrays.asList(customers);
	}
	
	public List<FinalTelephoneCallBuilder> createListOfTelephoneCalls(FinalTelephoneCallBuilder ... telephoneCallBuilders){
		return Arrays.asList(telephoneCallBuilders);
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
	
	public DateTime timeAndDate(int year, int month, int dayOfMonth, int hour, int minute){
		return new DateTime(year, month, dayOfMonth, hour, minute);
	}
	
	public int year(int year){
		return year;
	}
	
	public int month(int month){
		return month;
	}

	public int dayOfMonth(int dayOfMonth){
		return dayOfMonth;
	}
	
	public int hour(int hour){
		return hour;
	}
	
	public int minute(int minute){
		return minute;
	}
	
	public String generateBillUsingPrinter(List<Customer> customers ,
										   List<FinalTelephoneCallBuilder> telephoneCallBuilders, 
										   Printer printer){
		
		for (Customer c : customers){
			String tariffPlan = c.getPricePlan();
			printer.printHeading(c.getFullName(), c.getPhoneNumber(), tariffPlan);
			
			for(FinalTelephoneCallBuilder builder : telephoneCallBuilders){
				TelephoneCallRepresentation call = builder.getTelephoneCallRepresentation();
				if (call.getCallerName().equals(c.getFullName())){
					//extract stuff needed for print item.
					//printer.printItem(startTime, callee, duration, cost)
				}
			}
			
			
		}
		
		return "";
		
	}
}
