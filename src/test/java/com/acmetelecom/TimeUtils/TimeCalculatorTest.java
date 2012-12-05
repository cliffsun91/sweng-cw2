package com.acmetelecom.TimeUtils;

import com.acmetelecom.PeakOffPeakTime;
import junit.framework.Assert;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: deewar
 * Date: 05/12/12
 * Time: 02:21
 * To change this template use File | Settings | File Templates.
 */

public class TimeCalculatorTest {
    private PeakOffPeakPeriods peakOffPeakPeriods;
    public  TimeCalculatorTest() throws  FileParseException{
        peakOffPeakPeriods =  PeakOffPeakPeriods.
                loadPeakOffPeakPeriods(new File("src/test/java/com/acmetelecom/TimeUtils/times.xml"));
     }


    @Test
    public void SameEndAndStartTimes() throws Exception {
        DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  TimeCalculator.calculateTimes(startTime, endTime, peakOffPeakPeriods);
        Assert.assertEquals(time.getPeakTime(),0);
        Assert.assertEquals(time.getOffPeakTime(),0);

    }

    @Test
    public void testWithinOnePeriod() throws Exception {
        DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0900", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  TimeCalculator.calculateTimes(startTime, endTime, peakOffPeakPeriods);
        Assert.assertEquals(time.getPeakTime(),3600);
        Assert.assertEquals(time.getOffPeakTime(),0);

    }

    @Test
    public void beyondOnePeriod() throws Exception {
        DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("1100", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  TimeCalculator.calculateTimes(startTime, endTime, peakOffPeakPeriods);
        Assert.assertEquals(time.getPeakTime(),7200);
        Assert.assertEquals(time.getOffPeakTime(),3600);

    }

    @Test
    public void overlappingPeriod() throws Exception {
        DateTime startTime = DateTime.parse("0700", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("1300", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  TimeCalculator.calculateTimes(startTime, endTime, peakOffPeakPeriods);

        Assert.assertEquals(time.getPeakTime(),10800);
        Assert.assertEquals(time.getOffPeakTime(),10800);

    }

    @Test
    public void completelyOffPeak() throws Exception {
        DateTime startTime = DateTime.parse("0300", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0400", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  TimeCalculator.calculateTimes(startTime, endTime, peakOffPeakPeriods);

        Assert.assertEquals(time.getPeakTime(),0);
        Assert.assertEquals(time.getOffPeakTime(),3600);

    }
     // if calls last into the next day
    @Test
    public void EndTimeBeforeStartTime() throws Exception {
        DateTime startTime = DateTime.parse("0700", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0300", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  TimeCalculator.calculateTimes(startTime, endTime, peakOffPeakPeriods);
        Assert.assertEquals(time.getPeakTime(),21600);
        Assert.assertEquals(time.getOffPeakTime(),50400);

    }
}
