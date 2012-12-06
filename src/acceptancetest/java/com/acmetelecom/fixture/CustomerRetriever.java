package com.acmetelecom.fixture;

import java.util.List;

import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.microtype.Person;

public class CustomerRetriever {

	public static Customer getCustomerFromPerson(Person person, List<Customer> customers) throws CustomerNameMismatchException{
		for(Customer c : customers){
			if (c.getFullName().equals(person.getName())){
				return c;
			}
		}
		throw new CustomerNameMismatchException("Name not found in Customer list!");
	}
}
