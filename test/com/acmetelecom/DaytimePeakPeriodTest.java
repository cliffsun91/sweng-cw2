package com.acmetelecom;

import static org.junit.Assert.fail;

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
  public void testIsOffPeak() {
    calendar.set(Calendar.HOUR_OF_DAY, 5);
    assert (peakPeriod.offPeak(calendar.getTime()));
  }

  @Test
  public void testIsOffPeakBoundaryTime() {
    // Check what happens on the limit of peak -> offpeak
    calendar.set(Calendar.HOUR_OF_DAY, DaytimePeakPeriod.OFFPEAK_START);
    assert (peakPeriod.offPeak(calendar.getTime()));
    calendar.set(Calendar.HOUR_OF_DAY, DaytimePeakPeriod.OFFPEAK_END);
    assert (!peakPeriod.offPeak(calendar.getTime()));
  }

  @Test
  public void testGetTimeOffPeakOverlap() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetTimeOffPeakJustOffPeak() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetTimeOffPeakJustOnPeak() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetTimeOffPeakNoTime() {
    // Check what happens for zero-length calls
    fail("Not yet implemented");
  }

  @Test
  public void testGetTimePeak() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetTimePeakNoTime() {
    // Check what happens for zero-length calls
    fail("Not yet implemented");
  }

}
