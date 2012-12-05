package com.acmetelecom.TimeUtils;

import com.acmetelecom.PeakOffPeakTime;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

/**
* Created with IntelliJ IDEA.
* User: deewar
* Date: 05/12/12
* Time: 02:28
* To change this template use File | Settings | File Templates.
*/
public class TimeCalculator {

    private static  final PeakOffPeakPeriods PEAK_OFF_PEAK_PERIODS;

    static {
        PEAK_OFF_PEAK_PERIODS = PeakOffPeakPeriods.getDefaultPeakOffPeakPeriods();
    }


    private  TimeCalculator(){

    }

    public static PeakOffPeakTime calculateTimes(DateTime startTime,
                                                 DateTime endTime ) {
       return   calculateTimes(startTime,endTime,PEAK_OFF_PEAK_PERIODS);

    }

    public  static PeakOffPeakTime calculateTimes(DateTime startTime ,
                                                  DateTime endTime ,
                                                  PeakOffPeakPeriods peakOffPeakPeriods)
                                                  {
        if ( startTime.compareTo(endTime)>0){
            DateTime midnight = DateTime.parse("235959999", DateTimeFormat.forPattern("HHmmssSSS"));
            PeakOffPeakTime tillMidnight =TimeCalculator.calculateTimes(startTime,midnight,peakOffPeakPeriods) ;
            DateTime morning = DateTime.parse("0000", DateTimeFormat.forPattern("HHmm"));
            PeakOffPeakTime fromMorning =TimeCalculator.calculateTimes(morning,endTime,peakOffPeakPeriods) ;
            return new PeakOffPeakTime( tillMidnight.getPeakTime() + fromMorning.getPeakTime(),
                                        tillMidnight.getOffPeakTime()+ fromMorning.getOffPeakTime());
        }

        float  peakTime = 0 ;
        List<JodaTimePeriod> times = peakOffPeakPeriods.jodaTimes;
        for ( JodaTimePeriod period:times){
            if (startTime.compareTo(period.endTime) <= 0 &&
                    period.startTime.compareTo(endTime)<=0){
                DateTime earlierStart ;
                if ( startTime.compareTo( period.startTime) <=0){
                    earlierStart = period.startTime;
                }else{
                    earlierStart = startTime;
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
