package com.acmetelecom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.billcalculator.CustomerBillGenerator;
import com.acmetelecom.call.Call;
import com.acmetelecom.customer.Customer;
import com.acmetelecom.customer.Tariff;

@RunWith(JMock.class)
public class BillingSystemTest {

	private final Mockery context = new Mockery();
	private final CustomerBillGenerator customerBillGenerator = context.mock(CustomerBillGenerator.class);
	private final TariffStore tariffStore = context.mock(TariffStore.class);
	
	private BillingSystem billingSystem;
	
	@Before
	public void setup(){
		billingSystem = new BillingSystem();
	}
	
	@Test
	public void testCallInitiatedOnly() throws Exception {		
		billingSystem.callInitiated("Abz", "Cliff");
		
		HashMap<String, List<Call>> customerCallLog = billingSystem.getCustomersCallLog();
		List<Call> calls = customerCallLog.get("Abz");
		Call call = calls.get(0);
		Assert.assertEquals("Abz", call.getCaller());
		Assert.assertEquals("Cliff", call.getCallee());
		Assert.assertNotNull(call.getStartTime());
		Assert.assertNull(call.getEndTime());
	}

	
	@Test
	public void testCallCompletedOnly() throws Exception {
		billingSystem.callCompleted("Abz", "Cliff");
		
		HashMap<String, List<Call>> customerCallLog = billingSystem.getCustomersCallLog();
		List<Call> calls = customerCallLog.get("Abz");
		Assert.assertNull(calls);
	}
	
	@Test
	public void testCallInitiatedAndCompleted() throws Exception {
		billingSystem.callInitiated("Abz", "Cliff");
		billingSystem.callCompleted("Abz", "Cliff");
		
		HashMap<String, List<Call>> customerCallLog = billingSystem.getCustomersCallLog();
		List<Call> calls = customerCallLog.get("Abz");
		Call call = calls.get(0);
		Assert.assertEquals("Abz", call.getCaller());
		Assert.assertEquals("Cliff", call.getCallee());
		Assert.assertNotNull(call.getStartTime());
		Assert.assertNotNull(call.getEndTime());
	}
	
	@Test
	public void testCreateCustomerBills() throws Exception {

		final Customer customerA = new Customer("Jo King", "12345678", "Standard");
		final Customer customerB = new Customer("Robert Chatley", "87654321", "Standard");

		final Tariff tariff = Tariff.Standard;       

		context.checking(new Expectations(){{
			oneOf(tariffStore).getTariffFor(customerA); will(returnValue(tariff));
			oneOf(customerBillGenerator).createBillFor(customerA, null, tariff);
			
			oneOf(tariffStore).getTariffFor(customerB); will(returnValue(tariff));
			oneOf(customerBillGenerator).createBillFor(customerB, null, tariff);
		}});
			
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customerA);
		customers.add(customerB);
		
		new BillingSystem(customerBillGenerator,tariffStore, customers).createCustomerBills();
	}
}
