package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

class DaytimePeakPeriod {

  final static int OFFPEAK_START = 19; // 7pm
  final static int OFFPEAK_END = 7; // 7am

  // Ideally Offpeak start and end would be date values,
  // then we don't have to worry if they change from hours to
  // hours + minutes etc.
  public boolean offPeak(final Date time) {
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(time);
    final int hour = calendar.get(Calendar.HOUR_OF_DAY);
    return hour < OFFPEAK_END || hour >= OFFPEAK_START;
  }

  public int calcOffPeakTime(final Date startTime, final Date endTime,
      final int durationSeconds) {
    // Assuming no phone call lasts longer than 24 hours
    // Offpeak time is time outside peak period.
    // TODO
    return 0;
  }

  public int calcPeakTime(final Date startTime, final Date endTime,
      final int durationSeconds) {
    return durationSeconds
        - calcOffPeakTime(startTime, endTime, durationSeconds);
  }
}
