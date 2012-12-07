package com.acmetelecom.timeutils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class PeakOffPeakPeriodsTest {
    @Test
    public void testLoadBadFile() throws Exception {
        try{            
        	TimeConfigurationReader.loadPeakOffPeakPeriods(new File("src/test/java/com/acmetelecom/timeutils/timesbad.xml"));
        }catch ( FileParseException e){
            return;
        }
        Assert.fail("Loaded the incorrect file!");
    }

    @Test
    public void testLoadGoodFile() throws Exception {
        try{
           PeakOffPeakPeriods peakOffPeakPeriods =
                TimeConfigurationReader.loadPeakOffPeakPeriods(new File("src/test/java/com/acmetelecom/timeutils/timesTest.xml"));
            Assert.assertEquals(peakOffPeakPeriods.getJodaTimes().size(), 3);
            DateTime startTime = DateTime.parse("0800", DateTimeFormat.forPattern("HHmm"));
            DateTime endTime = DateTime.parse("1000", DateTimeFormat.forPattern("HHmm"));
            Assert.assertTrue(peakOffPeakPeriods.getJodaTimes().get(0).startTime.equals(startTime));
            Assert.assertTrue(peakOffPeakPeriods.getJodaTimes().get(0).endTime.equals(endTime));
        }catch ( FileParseException e){
            Assert.fail("unable to load file");
        }
    }


    @Test
    public void testGetDefaultPeakOffPeakPeriods() throws Exception {

            PeakOffPeakPeriods peakOffPeakPeriods =
                    TimeConfigurationReader.getDefaultPeakOffPeakPeriods();
            Assert.assertNotNull(peakOffPeakPeriods);

    }
}
