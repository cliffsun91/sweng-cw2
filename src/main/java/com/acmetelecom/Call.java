package com.acmetelecom;

import org.joda.time.DateTime;
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

  public int durationSeconds() {
    return (int) (((end.time() - start.time()) / 1000));
  }

  public String date() {
	DateTime time = new DateTime(start.time());
	DateTimeFormatter formatter = DateTimeFormat.forPattern("yy/M/d h:mma");
    return time.toString(formatter);
  }

  public DateTime startTime() {
    return new DateTime(start.time());
  }

  public DateTime endTime() {
    return new DateTime(end.time());
  }
}
