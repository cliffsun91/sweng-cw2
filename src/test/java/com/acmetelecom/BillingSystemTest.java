//package com.acmetelecom;
//
//import static org.junit.Assert.fail;
//
//import org.jmock.Mockery;
//import org.jmock.integration.junit4.JMock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import com.acmetelecom.customer.Customer;
//import com.acmetelecom.customer.Tariff;
//
//
//// Is it possible to use mocking here?
//// Yes it's needed to fabricate customer calls.
//@RunWith(JMock.class)
//public class BillingSystemTest {
//
//  final Mockery context = new Mockery();
//  final Customer customer = context.mock(Customer.class);
//  final Tariff tariff = context.mock(Tariff.class);
//
//  @Test
//  public void testPeakRate() throws Exception {
//    // Test that when a call is made peakrate,
//    // the call is charged completely on peak
//    //fail("Not yet implemented");
//  }
//
//  @Test
//  public void testOffPeakRate() throws Exception {
//    // Test that when a call is made offpeak,
//    // the whole call is charged off peak
//    //fail("Not yet implemented");
//  }
//
//  @Test
//  public void testOffPeakToPeakRate() throws Exception {
//    //fail("Not yet implemented");
//  }
//
//  @Test
//  public void testPeakToOffPeakRate() throws Exception {
//    //fail("Not yet implemented");
//  }
//
//}
