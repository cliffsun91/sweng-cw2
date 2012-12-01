package com.acmetelecom.call;

import org.hamcrest.CoreMatchers;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.acmetelecom.callevent.CallEvent;

@RunWith(JMock.class)
public class CallTest {
	
	final Mockery context = new Mockery();
	final CallEvent callStart = context.mock(CallEvent.class, "CallEvent1");
	final CallEvent callEnd = context.mock(CallEvent.class, "CallEvent2");
	final Call call = new Call(callStart, callEnd);
	
	@Before
	public void setup(){

	}
	
	@Test
	public void testGetCalleeGetsCalleeFromCallStart(){
		final String callee = "Cliff";
		
		context.checking(new Expectations() {{
            oneOf(callStart).getCallee(); will(returnValue(callee));
        }});
		
		Assert.assertThat(call.callee(), CoreMatchers.equalTo(callee));	
	}
}
