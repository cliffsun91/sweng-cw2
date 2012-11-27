package com.acmetelecom;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

// Is it possible to use mocking here?
public class DaytimePeakPeriodTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testIsOffPeak() {
    fail("Not yet implemented");
  }

  @Test
  public void testIsOffPeakBoundaryTime() {
    // Check what happens on the limit of peak -> offpeak
    fail("Not yet implemented");
  }

  @Test
  public void testGetTimeOffPeak() {
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
