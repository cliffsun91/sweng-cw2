package com.acmetelecom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

// Is it possible to use mocking here?
public class DaytimePeakPeriodTest {

  DaytimePeakPeriod peakPeriod;
  DateTime time;
  DateTime newTime;

  @Before
  public void setUp() throws Exception {
    peakPeriod = new DaytimePeakPeriod();
    time = new DateTime();
  }

  @Test
  public void testThat5amIsOffPeak() {
	newTime = time.withHourOfDay(5);
    assertTrue(peakPeriod.offPeak(newTime));
  }
  
  @Test
  public void testThat10amIsNotOffPeak(){
	newTime = time.withHourOfDay(10);
	assertFalse(peakPeriod.offPeak(newTime));
  }

  @Test
  public void testIsOffPeakBoundaryTime() {
    // Check what happens on the limit of peak -> offpeak
    newTime = time.withHourOfDay(DaytimePeakPeriod.OFFPEAK_START);
    assertTrue (peakPeriod.offPeak(newTime));
    newTime = time.withHourOfDay(DaytimePeakPeriod.OFFPEAK_END);
    assertFalse (peakPeriod.offPeak(newTime));
  }
  
}
