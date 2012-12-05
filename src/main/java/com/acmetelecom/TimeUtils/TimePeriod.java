package com.acmetelecom.TimeUtils;

import javax.xml.bind.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * User: oaj09
 * Date: 04/12/12
 * Time: 04:23
 * To change this template use File | Settings | File Templates.
 */

//stores the start and the end time. The time must be between 00:00 and 24:00
//can potentially be extended to remember the days in the week as well.


@XmlType
@XmlRootElement(name = "TimePeriod")
public class TimePeriod {
    public String startTime;
    public String endTime;
}
