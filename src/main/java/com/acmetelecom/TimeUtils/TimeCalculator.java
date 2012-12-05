package com.acmetelecom.TimeUtils;

import com.acmetelecom.PeakOffPeakTime;
import org.joda.time.DateTime;

import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: deewar
* Date: 05/12/12
* Time: 02:28
* To change this template use File | Settings | File Templates.
*/
public class TimeCalculator {

    private PeakOffPeakPeriods peakOffPeakPeriods;

    public TimeCalculator(){
        this.peakOffPeakPeriods = PeakOffPeakPeriods.getDefaultPeakOffPeakPeriods();
    }
    public  TimeCalculator(PeakOffPeakPeriods peakOffPeakPeriods){

        this.peakOffPeakPeriods = peakOffPeakPeriods;
    }


    public  PeakOffPeakTime calculateTimes(DateTime startTime , DateTime endTime){
        float  peakTime = 0 ;
        List<JodaTimePeriod> times = peakOffPeakPeriods.jodaTimes;
        for ( JodaTimePeriod period:times){
            if (startTime.compareTo(period.endTime) <= 0 &&
                    period.startTime.compareTo(endTime)<=0){
                DateTime earlierStart ;
                if ( startTime.compareTo( period.startTime) <=0){
                    earlierStart = startTime;
                }else{
                    earlierStart = period.startTime;
                }
                DateTime earlierEnd ;
                if ( endTime.compareTo( period.endTime) <=0){
                    earlierEnd = endTime;
                }else{
                    earlierEnd = period.endTime;
                }
                peakTime += (earlierEnd.getMillis() -earlierStart.getMillis());
            }
        }
        float offpeak = endTime.getMillis() - startTime.getMillis();
        offpeak -= peakTime;
        return new PeakOffPeakTime((long)peakTime/1000,(long)offpeak/1000);
    }

}
