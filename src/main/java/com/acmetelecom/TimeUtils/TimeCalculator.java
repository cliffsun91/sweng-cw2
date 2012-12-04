package com.acmetelecom.TimeUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "TimeCalculator")
@XmlSeeAlso({TimePeriod.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class TimeCalculator {







    //Code to read in a file

    public  final List<TimePeriod> times = new ArrayList<TimePeriod>() ;

    private static  final TimeCalculator timeCalculator;

    private  TimeCalculator(){

    }
    public static TimeCalculator getInstance(){
        return  timeCalculator;
    }
    static  {
        TimeCalculator tc = new TimeCalculator();

        try{
            JAXBContext context = JAXBContext.newInstance(TimeCalculator.class);
            Unmarshaller um = context.createUnmarshaller()      ;
            tc = (TimeCalculator)um.unmarshal(new File("src/main/java/com/acmetelecom/TimeUtils/times.xml"));

        }catch (Exception e){
            System.err.println("Unable to load time periods. Please restart\nException\n" +e.toString());
            e.printStackTrace();
            System.exit(-1);
        }
        timeCalculator = tc;
    }
    public  static void  main(String args[])throws Exception{
        JAXBContext context = JAXBContext.newInstance(TimeCalculator.class);
        final Marshaller marshaller = context.createMarshaller();
//        TimeCalculator tc = new TimeCalculator();
//        TimePeriod tp = new TimePeriod();
//        tp.endTime ="end";
//        tp.startTime= "start";
//        tc.times.add(tp);
        marshaller.marshal(TimeCalculator.getInstance(),System.out);


    }


}
