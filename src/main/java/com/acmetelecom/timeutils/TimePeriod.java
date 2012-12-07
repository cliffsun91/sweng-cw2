package com.acmetelecom.timeutils;

import javax.xml.bind.annotation.*;

//stores the start and the end time. The time must be between 00:00 and 24:00
//can potentially be extended to remember the days in the week as well.


@XmlType
@XmlRootElement(name = "TimePeriod")
public class TimePeriod {
    public String startTime;
    public String endTime;
}
