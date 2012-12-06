package com.acmetelecom.fixture;

import static com.acmetelecom.fixture.CustomerRetriever.getCustomerFromPerson;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.assertion.AcmeTelecomTestAssertions;
import com.acmetelecom.assertion.CustomersBuilder;
import com.acmetelecom.billingsystembuilder.BillingSystemBuilder;
import com.acmetelecom.billingsystembuilder.TelephoneCallsBuilder;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.microtype.Person;
import com.acmetelecom.microtype.TariffPlan;
import com.acmetelecom.microtype.TelephoneNumber;
import com.acmetelecom.printbillbuilder.AddCallsToBillBuilder;
import com.acmetelecom.printbillbuilder.SingleCustomerBillBuilder;
import com.acmetelecom.printer.Printer;
import com.acmetelecom.telephonecallbuilder.DefaultTelephoneCallBuilder;
import com.acmetelecom.telephonecallbuilder.FromCallerBuilder;
import com.acmetelecom.telephonecallbuilder.TelephoneCallRepresentation;

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
	
	public List<TelephoneCallRepresentation> createListOfTelephoneCalls(TelephoneCallRepresentation ... calls){
		return Arrays.asList(calls);
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
	
	public DateTime startTime(DateTime time){
		return time;
	}
	
	public DateTime endTime(DateTime time){
		return time;
	}
	
	public Customer toReceiver(Customer customer){
		return customer;
	}
	
	public String withCost(String cost){
		return cost;
	}
	
	public String aBill(String ... customerBills){
		String fullBill = "";
		for(String bill : customerBills){
			fullBill += bill;
		}
		return fullBill;
	}
	
	public Customer customer(Person person, List<Customer> customerList) throws CustomerNameMismatchException{
		return getCustomerFromPerson(person, customerList);
	}
	
	public AddCallsToBillBuilder forCustomer(Customer customer, Printer printer){
		return new SingleCustomerBillBuilder(customer, printer);
	}
	
	public Printer usingPrinter (Printer printer){
		return printer;
	}
	
}
