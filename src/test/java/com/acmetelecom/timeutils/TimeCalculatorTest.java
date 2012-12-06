package com.acmetelecom.timeutils;

import junit.framework.Assert;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Test;

import java.io.File;

public class TimeCalculatorTest {
    private IPeakOffPeakPeriods peakOffPeakPeriods;
    private TimeCalculator timeCalculator;
    public  TimeCalculatorTest() throws  FileParseException{
        peakOffPeakPeriods =  PeakOffPeakPeriods.
                loadPeakOffPeakPeriods(new File("src/test/java/com/acmetelecom/timeutils/timesTest.xml"));
        timeCalculator = new TimeCalculator(peakOffPeakPeriods);
    }


    @Test
    public void SameEndAndStartTimes() throws Exception {
        DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
        Assert.assertEquals(time.getPeakTime(),0);
        Assert.assertEquals(time.getOffPeakTime(),0);

    }

    @Test
    public void testWithinOnePeriod() throws Exception {
        DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0900", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
        Assert.assertEquals(time.getPeakTime(),3600);
        Assert.assertEquals(time.getOffPeakTime(),0);

    }

    @Test
    public void beyondOnePeriod() throws Exception {
        DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("1100", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
        Assert.assertEquals(time.getPeakTime(),7200);
        Assert.assertEquals(time.getOffPeakTime(),3600);

    }

    @Test
    public void overlappingPeriod() throws Exception {
        DateTime startTime = DateTime.parse("0700", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("1300", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);

        Assert.assertEquals(time.getPeakTime(),10800);
        Assert.assertEquals(time.getOffPeakTime(),10800);

    }

    @Test
    public void completelyOffPeak() throws Exception {
        DateTime startTime = DateTime.parse("0300", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0400", DateTimeFormat.forPattern("HHmm"));

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);

        Assert.assertEquals(time.getPeakTime(),0);
        Assert.assertEquals(time.getOffPeakTime(),3600);

    }
     // if calls last into the next day
    @Test
    public void EndTimeBeforeStartTime() throws Exception {
        DateTime startTime = DateTime.parse("0700", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0300", DateTimeFormat.forPattern("HHmm")).plusDays(1);

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
        Assert.assertEquals(time.getPeakTime(),21600);
        Assert.assertEquals(time.getOffPeakTime(),50400);

    }




    @Test
    public void CallLargerThanOneDay() throws Exception {
        DateTime startTime = DateTime.parse("0700", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0300", DateTimeFormat.forPattern("HHmm")).plusDays(1);

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
        Assert.assertEquals(time.getPeakTime(),21600);
        Assert.assertEquals(time.getOffPeakTime(),50400);

    }

    @Test
    public void CallLargerThanTwoDays() throws Exception {
        DateTime startTime = DateTime.parse("0700", DateTimeFormat.forPattern("HHmm"));
        DateTime endTime = DateTime.parse("0300", DateTimeFormat.forPattern("HHmm")).plusDays(3);

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
        Assert.assertEquals(time.getPeakTime(),64800);
        Assert.assertEquals(time.getOffPeakTime(),180000);

    }

    @Test
    public void callWithCurrentDates() throws Exception {
        DateTime startTime = DateTime.now();
        DateTime endTime =   startTime.plusHours(24);

        PeakOffPeakTime time=  timeCalculator.calculateTimes(startTime, endTime);
//        Assert.assertEquals(time.getPeakTime(),64800);
        System.out.println(time.getOffPeakTime());
        System.out.println(time.getPeakTime());


//        Assert.assertEquals(time.getOffPeakTime(),194400);

    }
}
