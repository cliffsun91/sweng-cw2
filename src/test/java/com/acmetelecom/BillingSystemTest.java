package com.acmetelecom;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.acmetelecom.call.CallTime;

public class BillingSystemTest {

	private BillingSystem billingSystem;
	
	@Before
	public void setup(){
		billingSystem = new BillingSystem();
	}
	
	@Test
	public void testCallInitiatedOnly() throws Exception {		
		billingSystem.callInitiated("Abz", "Cliff");
		
		HashMap<String, List<CallTime>> customerCallLog = billingSystem.getCustomersCallLog();
		List<CallTime> calls = customerCallLog.get("Abz");
		CallTime call = calls.get(0);
		Assert.assertEquals("Abz", call.getCaller());
		Assert.assertEquals("Cliff", call.getCallee());
		Assert.assertNotNull(call.getStartTime());
		Assert.assertNull(call.getEndTime());
	}

	
	@Test
	public void testCallCompletedOnly() throws Exception {
		billingSystem.callCompleted("Abz", "Cliff");
		
		HashMap<String, List<CallTime>> customerCallLog = billingSystem.getCustomersCallLog();
		List<CallTime> calls = customerCallLog.get("Abz");
		Assert.assertNull(calls);
	}
	
	@Test
	public void testCallInitiatedAndCompleted() throws Exception {
		billingSystem.callInitiated("Abz", "Cliff");
		billingSystem.callCompleted("Abz", "Cliff");
		
		HashMap<String, List<CallTime>> customerCallLog = billingSystem.getCustomersCallLog();
		List<CallTime> calls = customerCallLog.get("Abz");
		CallTime call = calls.get(0);
		Assert.assertEquals("Abz", call.getCaller());
		Assert.assertEquals("Cliff", call.getCallee());
		Assert.assertNotNull(call.getStartTime());
		Assert.assertNotNull(call.getEndTime());
	}

}
