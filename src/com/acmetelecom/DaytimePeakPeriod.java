package com.acmetelecom;

import java.util.Calendar;
import java.util.Date;

class DaytimePeakPeriod {

  final static int OFFPEAK_START = 7;
  final static int OFFPEAK_END = 19;

  // Ideally Offpeak start and end would be date values,
  // then we don't have to worry if they change from hours to
  // hours + minutes etc.
  public boolean offPeak(final Date time) {
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(time);
    final int hour = calendar.get(Calendar.HOUR_OF_DAY);
    return hour < OFFPEAK_START || hour >= OFFPEAK_END;
  }

  public int calcOffPeakTime(final Date startTime, final Date endTime,
      final int durationSeconds) {
    // Assuming no phone call lasts longer than 24 hours
    // Offpeak time is time outside peak period.

    int duration = 0;
    final Calendar calendar = Calendar.getInstance();
    calendar.setTime(startTime);
    if (offPeak(startTime)) {
      final int hours = calendar.get(Calendar.HOUR_OF_DAY);
      final int minutes = calendar.get(Calendar.MINUTE);
      final int seconds = calendar.get(Calendar.SECOND);
      duration += (OFFPEAK_START * 60 * 60)
          - ((hours * 60 * 60) + (minutes * 60) + seconds);
    }
    calendar.setTime(endTime);
    if (offPeak(endTime)) {
      final int hours = calendar.get(Calendar.HOUR_OF_DAY);
      final int minutes = calendar.get(Calendar.MINUTE);
      final int seconds = calendar.get(Calendar.SECOND);
      duration += ((hours * 60 * 60) + (minutes * 60) + seconds)
          - (OFFPEAK_END * 60 * 60);
    }
    return duration;
  }

  public int calcPeakTime(final Date startTime, final Date endTime,
      final int durationSeconds) {
    return durationSeconds
        - calcOffPeakTime(startTime, endTime, durationSeconds);
  }
}
