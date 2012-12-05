package com.acmetelecom.call;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.acmetelecom.callevent.CallEvent;

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
	DateTimeFormatter formatter = DateTimeFormat.forPattern("yy/M/d h:mma");
    return time.toString(formatter);
  }

  public DateTime startTime() {
    return start.getTimestamp();
  }

  public DateTime endTime() {
    return end.getTimestamp();
  }
}
