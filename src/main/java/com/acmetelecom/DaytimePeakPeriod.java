package com.acmetelecom;

import org.joda.time.DateTime;

class DaytimePeakPeriod {

  final static int OFFPEAK_START = 19; // 7pm
  final static int OFFPEAK_END = 7; // 7am

  // Ideally Offpeak start and end would be date values,
  // then we don't have to worry if they change from hours to
  // hours + minutes etc.
  public boolean offPeak(final DateTime time) {
    final int hour = time.getHourOfDay();
    return hour < OFFPEAK_END || hour >= OFFPEAK_START;
  }

  private int calculateOffPeakTime(final DateTime startTime, final DateTime endTime,
                                  final int durationSeconds) {
    // Assuming no phone call lasts longer than 24 hours
    // Offpeak time is time outside peak period.
    // TODO
    return 0;
  }


  public int calculatePeakTime(final DateTime startTime, final DateTime endTime,
                               final int durationSeconds) {
    return durationSeconds
        - calculateOffPeakTime(startTime, endTime, durationSeconds);
  }
}
