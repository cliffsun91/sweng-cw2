package com.acmetelecom.telephonecallbuilder;

import static com.acmetelecom.callevent.CallEnd.endCall;
import static com.acmetelecom.callevent.CallStart.startCall;
import static com.acmetelecom.fixture.CustomerRetriever.getCustomerFromPerson;

import java.util.List;

import org.joda.time.DateTime;

import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.exception.CustomerNameMismatchException;
import com.acmetelecom.fixture.CustomerRetriever;
import com.acmetelecom.microtype.Person;

public class DefaultTelephoneCallBuilder implements FromCallerBuilder, 
											 ToReceiverBuilder, 
											 StartTimeBuilder, 
											 EndTimeBuilder,
											 FinalTelephoneCallBuilder{

	private Person caller;
	private Person receiver;
	private DateTime startTime;
	private DateTime endTime;

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
	public EndTimeBuilder withStartTime(DateTime startTime) {
		this.startTime = startTime;
		return this;
	}

	@Override
	public FinalTelephoneCallBuilder andWithEndTime(DateTime endTime) {
		this.endTime = endTime;
		return this;
	}

	private CallStart buildStartCall(List<Customer> customers) throws CustomerNameMismatchException {
		String callerTelephoneNumber = getCustomerFromPerson(caller, customers).getPhoneNumber();
		String receiverTelephoneNumber = getCustomerFromPerson(receiver, customers).getPhoneNumber();
		return new CallStart(callerTelephoneNumber, receiverTelephoneNumber, startTime);
	}

	private CallEnd buildEndCall(List<Customer> customers) throws CustomerNameMismatchException {
		String callerTelephoneNumber = getCustomerFromPerson(caller, customers).getPhoneNumber();
		String receiverTelephoneNumber = getCustomerFromPerson(receiver, customers).getPhoneNumber();
		return new CallEnd(callerTelephoneNumber, receiverTelephoneNumber, endTime);
	}
	
	@Override
	public TelephoneCallRepresentation endCall(List<Customer> customers) throws CustomerNameMismatchException{
		return new TelephoneCallRepresentation(buildStartCall(customers), buildEndCall(customers));
	}

}
