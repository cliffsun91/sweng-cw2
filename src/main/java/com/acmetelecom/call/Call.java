package com.acmetelecom.call;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    return SimpleDateFormat.getInstance().format(new Date(start.time()));
  }

  public Date startTime() {
    return new Date(start.time());
  }

  public Date endTime() {
    return new Date(end.time());
  }
}
