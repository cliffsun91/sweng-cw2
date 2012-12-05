package com.acmetelecom.TimeUtils;

import com.acmetelecom.PeakOffPeakTime;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: deewar
 * Date: 05/12/12
 * Time: 02:28
 * To change this template use File | Settings | File Templates.
 */
public class TimeCalculator {

    private  final IPeakOffPeakPeriods peakOffPeakPeriods;


    public TimeCalculator() {
        peakOffPeakPeriods = PeakOffPeakPeriods.getDefaultPeakOffPeakPeriods();
    }

    public TimeCalculator(IPeakOffPeakPeriods peakOffPeakPeriods){
        this.peakOffPeakPeriods = peakOffPeakPeriods;
    }


    public PeakOffPeakTime calculateTimes(DateTime startTime,
                                                 DateTime endTime) throws IllegalArgumentException {
        if (endTime.compareTo(startTime)<0 ){
            throw new IllegalArgumentException("start time must be less than equal to the end time");
        }
        float peakTime = 0;
        int days = Days.daysBetween(startTime, endTime).getDays();
        List<JodaTimePeriod> times = peakOffPeakPeriods.getJodaTimes();
        for (int i = 0; i <= days; i++) {
            for (JodaTimePeriod period : times) {
                if (startTime.compareTo(period.endTime) <= 0 &&
                        period.startTime.compareTo(endTime) <= 0) {
                    DateTime earlierStart;
                    if (startTime.compareTo(period.startTime) <= 0) {
                        earlierStart = period.startTime;
                    } else {
                        earlierStart = startTime;
                    }
                    DateTime earlierEnd;
                    if (endTime.compareTo(period.endTime) <= 0) {
                        earlierEnd = endTime;
                    } else {
                        earlierEnd = period.endTime;
                    }
                    peakTime += (earlierEnd.getMillis() - earlierStart.getMillis());
                }
            }
        }
        float offpeak = endTime.getMillis() - startTime.getMillis();
        offpeak -= peakTime;
        return new PeakOffPeakTime((long) peakTime / 1000, (long) offpeak / 1000);
    }

}
