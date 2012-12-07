package com.acmetelecom.timeutils;

import org.joda.time.DateTime;

public class JodaTimePeriod {
    public JodaTimePeriod(DateTime startTime,DateTime endTime)throws Exception{
        this.startTime = startTime;
        this.endTime = endTime;
        if ( startTime.compareTo(endTime) >=0){
            throw new Exception("Start time must be less than the end time");
        }
    }
    public DateTime startTime;
    public DateTime endTime;
}
