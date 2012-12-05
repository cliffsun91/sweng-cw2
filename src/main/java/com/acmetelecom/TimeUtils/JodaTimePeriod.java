package com.acmetelecom.TimeUtils;

import org.joda.time.DateTime;

/**
 * Created with IntelliJ IDEA.
 * User: deewar
 * Date: 05/12/12
 * Time: 01:08
 * To change this template use File | Settings | File Templates.
 */
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
