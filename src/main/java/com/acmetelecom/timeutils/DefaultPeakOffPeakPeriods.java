package com.acmetelecom.timeutils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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

}
