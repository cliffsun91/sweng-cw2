package com.acmetelecom.timeutils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "PeakOffPeakPeriods")
@XmlSeeAlso({TimePeriod.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class DefaultPeakOffPeakPeriods implements PeakOffPeakPeriods {


    @Override
    public List<JodaTimePeriod> getJodaTimes() {
        return jodaTimes;
    }

    private final List<JodaTimePeriod> jodaTimes = new ArrayList<JodaTimePeriod>();
    public  final List<TimePeriod> times = new ArrayList<TimePeriod>() ;
    private static  final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("HHmm");
    private static final JAXBContext context ;
    private static final Unmarshaller unmarshaller;
    private static final String DEFAULT_PATH ="src/main/java/com/acmetelecom/timeutils/times.xml";
    private static final PeakOffPeakPeriods PEAK_OFF_PEAK_PERIODS ;


    public static PeakOffPeakPeriods loadPeakOffPeakPeriods(File file)throws FileParseException{

        try{
             DefaultPeakOffPeakPeriods peakOffPeakPeriods=
                     (DefaultPeakOffPeakPeriods)unmarshaller.unmarshal(file);
             peakOffPeakPeriods.initializeJodaTimePeriods();
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
            c = JAXBContext.newInstance(DefaultPeakOffPeakPeriods.class);
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

    private void initializeJodaTimePeriods() throws Exception {
        for ( TimePeriod period: times){
             JodaTimePeriod jperiod = new
                        JodaTimePeriod(DateTime.parse(period.startTime,FORMATTER),
                                       DateTime.parse(period.endTime,FORMATTER));
             jodaTimes.add(jperiod);
         }
        normalizeJodaTimePeriods();
    }

    private void normalizeJodaTimePeriods() throws Exception {
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
                   }
               }
       }
    }


    public  static void  main(String args[])throws Exception{
//        final Marshaller marshaller = context.createMarshaller();
//        PeakOffPeakPeriods p = new PeakOffPeakPeriods();
//        TimePeriod tp = new TimePeriod();
//        tp.endTime ="0700";
//        tp.startTime= "0900";
//        p.times.add(tp);
        context.createMarshaller().marshal(getDefaultPeakOffPeakPeriods(), System.out);


    }


}
