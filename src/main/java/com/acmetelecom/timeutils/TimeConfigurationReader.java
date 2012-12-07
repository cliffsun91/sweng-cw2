package com.acmetelecom.timeutils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "PeakOffPeakPeriods")
@XmlSeeAlso({TimePeriod.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeConfigurationReader {
    //this list was required for parsing xml. JAXB framework does not support joda time by default
    //therefore we thought it would be better to parse strings to a list and convert them to joda
    //times. We could have used converters for JAXB but we thought that this was not the point of
    //this exercise.
    public  final List<TimePeriod> times = new ArrayList<TimePeriod>() ;

    //The XML contains This Format
    private static  final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("HHmm");
    private static final JAXBContext context ;
    private static final Unmarshaller unmarshaller;
    private static final String DEFAULT_PATH ="src/main/java/com/acmetelecom/timeutils/times.xml";
    private static final PeakOffPeakPeriods PEAK_OFF_PEAK_PERIODS ;


    public static PeakOffPeakPeriods loadPeakOffPeakPeriods(File file)throws FileParseException{

        try{
            TimeConfigurationReader timeConfigurationReader=
                    (TimeConfigurationReader)unmarshaller.unmarshal(file);
            PeakOffPeakPeriods peakOffPeakPeriods = new DefaultPeakOffPeakPeriods();
            initializeJodaTimePeriods(timeConfigurationReader,peakOffPeakPeriods);
            return peakOffPeakPeriods;
        }catch (Exception e){
            throw new FileParseException("Unable to load times from the file provided" ,e);
        }

    }



    public static PeakOffPeakPeriods getDefaultPeakOffPeakPeriods(){
        return  PEAK_OFF_PEAK_PERIODS;
    }

    static  {
        JAXBContext c = null;
        Unmarshaller u = null;
        PeakOffPeakPeriods p = null;
        try{
            c = JAXBContext.newInstance(TimeConfigurationReader.class);
            u = c.createUnmarshaller();


        }catch (Exception e){
            System.err.println("Unable to initialize JaxB. Please restart\nException\n" +e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
        context =c;
        unmarshaller =u;

        try{
            p = loadPeakOffPeakPeriods(new File(DEFAULT_PATH));
        }catch (FileParseException e){
            System.err.println("Unable to load Default time periods. Please restart\nException\n" +e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
        PEAK_OFF_PEAK_PERIODS = p;
    }

    private static void initializeJodaTimePeriods(TimeConfigurationReader timeConfigurationReader, PeakOffPeakPeriods peakOffPeakPeriods) throws Exception {
        List<JodaTimePeriod> jodaTimes = peakOffPeakPeriods.getJodaTimes();

        for ( TimePeriod period: timeConfigurationReader.times){
            JodaTimePeriod jperiod = new
                    JodaTimePeriod(DateTime.parse(period.startTime, FORMATTER),
                    DateTime.parse(period.endTime,FORMATTER));
            jodaTimes.add(jperiod);
        }
        normalizeJodaTimePeriods(peakOffPeakPeriods);
    }

    private static void normalizeJodaTimePeriods(PeakOffPeakPeriods peakOffPeakPeriods) throws Exception {
        List<JodaTimePeriod> jodaTimes = peakOffPeakPeriods.getJodaTimes();

        for ( int i =0 ; i < jodaTimes.size(); i++){
            JodaTimePeriod timePeriod = jodaTimes.get(i);
            for ( int j = 0; j <jodaTimes.size(); j++){
                if ( i == j ){
                    continue;
                }
                JodaTimePeriod innerTimePeriod = jodaTimes.get(j);
                if (timePeriod.startTime.compareTo(innerTimePeriod.endTime) <= 0 &&
                        innerTimePeriod.startTime.compareTo(timePeriod.endTime)<=0){
                    if ( timePeriod.startTime.compareTo(innerTimePeriod.startTime) <0){
                        innerTimePeriod.startTime = timePeriod.startTime;
                    }
                    if ( timePeriod.endTime.compareTo(innerTimePeriod.endTime) > 0){
                        innerTimePeriod.endTime  = timePeriod.endTime;
                    }
                    jodaTimes.remove(i);
                    normalizeJodaTimePeriods(peakOffPeakPeriods);
                    return;
                }
            }
        }
    }



    public  static void  main(String args[])throws Exception{
        TimeConfigurationReader t = new TimeConfigurationReader();
        TimePeriod tp = new TimePeriod();
        tp.endTime ="0700";
        tp.startTime= "0900";
        t.times.add(tp);
        context.createMarshaller().marshal(t, System.out);


    }

}
