package com.telecom.telephonecallbuilder;

import static com.acmetelecom.callevent.CallEnd.endCall;
import static com.acmetelecom.callevent.CallStart.startCall;

import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.microtype.Person;

public class DefaultTelephoneCallBuilder implements FromCallerBuilder, 
											 ToReceiverBuilder, 
											 StartTimeBuilder, 
											 EndTimeBuilder,
											 FinalTelephoneCallBuilder{

	Person caller;
	Person receiver;
	int startTime;
	int endTime;

	@Override
	public ToReceiverBuilder fromCaller(Person caller) {
		this.caller = caller;
		return this;
	}
	
	@Override
	public StartTimeBuilder toReceiver(Person receiver) {
		this.receiver = receiver;
		return this;
	}

	@Override
	public EndTimeBuilder withStartTime(int startTime) {
		this.startTime = startTime;
		return this;
	}

	@Override
	public FinalTelephoneCallBuilder andWithEndTime(int endTime) {
		this.endTime = endTime;
		return this;
	}

	@Override
	public CallStart buildStartCall(List<Customer> customers) throws CustomerNameMismatchException {
		String callerTelephoneNumber = getTelephoneNumberForPerson(caller, customers);
		String receiverTelephoneNumber = getTelephoneNumberForPerson(receiver, customers);
		DateTime time = DateTime.now();
		DateTime newTime = time.withHourOfDay(startTime);
		return startCall(callerTelephoneNumber, receiverTelephoneNumber, newTime);
	}

	@Override
	public CallEnd buildEndCall(List<Customer> customers) throws CustomerNameMismatchException {
		String callerTelephoneNumber = getTelephoneNumberForPerson(caller, customers);
		String receiverTelephoneNumber = getTelephoneNumberForPerson(receiver, customers);
		DateTime time = DateTime.now();
		DateTime newTime = time.withHourOfDay(endTime);
		return endCall(callerTelephoneNumber, receiverTelephoneNumber, newTime);
	}

	public String getTelephoneNumberForPerson(Person person, List<Customer> customers) throws CustomerNameMismatchException{
		for(Customer c : customers){
			if (c.getFullName().equals(person.getName())){
				return c.getPhoneNumber();
			}
		}
		throw new CustomerNameMismatchException("Name not found in Customer list!");
	}
}