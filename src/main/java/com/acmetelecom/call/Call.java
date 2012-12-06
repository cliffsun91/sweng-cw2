package com.acmetelecom.call;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.acmetelecom.callevent.CallEvent;
import com.acmetelecom.timeutils.TimeFormatter;

public class Call {
  private final CallEvent start;
  private final CallEvent end;

  public Call(final CallEvent start, final CallEvent end) {
    this.start = start;
    this.end = end;
  }

  public String callee() {
    return start.getCallee();
  }

  public Duration duration() {
    return new Duration(start.getTimestamp(), end.getTimestamp());
  }

  public String date() {
	DateTime time = new DateTime(start.getTimestamp());
	return TimeFormatter.formatDateTime(time);
  }

  public DateTime startTime() {
    return start.getTimestamp();
  }

  public DateTime endTime() {
    return end.getTimestamp();
  }
}
