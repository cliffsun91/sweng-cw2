package com.acmetelecom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

// Is it possible to use mocking here?
public class DaytimePeakPeriodTest {

  DaytimePeakPeriod peakPeriod;
  Calendar calendar;

  @Before
  public void setUp() throws Exception {
    peakPeriod = new DaytimePeakPeriod();
    calendar = Calendar.getInstance();
  }

  @Test
  public void testThat5amIsOffPeak() {
    calendar.set(Calendar.HOUR_OF_DAY, 5);
    assertTrue(peakPeriod.offPeak(calendar.getTime()));
  }
  
  @Test
  public void testThat10amIsNotOffPeak(){
	calendar.set(Calendar.HOUR_OF_DAY, 10);
	assertFalse(peakPeriod.offPeak(calendar.getTime()));
  }

  @Test
  public void testIsOffPeakBoundaryTime() {
    // Check what happens on the limit of peak -> offpeak
    calendar.set(Calendar.HOUR_OF_DAY, DaytimePeakPeriod.OFFPEAK_START);
    assertTrue (peakPeriod.offPeak(calendar.getTime()));
    calendar.set(Calendar.HOUR_OF_DAY, DaytimePeakPeriod.OFFPEAK_END);
    assertFalse (peakPeriod.offPeak(calendar.getTime()));
  }
  
}
