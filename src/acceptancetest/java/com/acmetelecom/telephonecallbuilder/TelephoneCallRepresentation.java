package com.acmetelecom.telephonecallbuilder;

import com.acmetelecom.callevent.CallEnd;
import com.acmetelecom.callevent.CallStart;
import com.acmetelecom.microtype.Person;
import com.acmetelecom.timeutils.TimeFormatter;

public class TelephoneCallRepresentation {

	CallStart startCall;
	CallEnd endCall;
	
	public TelephoneCallRepresentation(CallStart startCall, CallEnd endCall) {
		this.startCall = startCall;
		this.endCall = endCall;
	}
	
	public CallStart getStartCall(){
		return startCall;
	}
	
	public CallEnd getEndCall(){
		return endCall;
	}
	
	public String getCalleeNumber(){
		return startCall.getCallee();
	}
	
	public String getStartTimeRepresentation(){
		return TimeFormatter.formatDateTime(startCall.getTimestamp());
	}
	
	public String getDurationMinutes(){
		long seconds = calculateCallDurationInSeconds();
		return TimeFormatter.formatMinutesFromSeconds(seconds);
	}
	
	private long calculateCallDurationInSeconds(){
		return (endCall.getTimestamp().getMillis() - startCall.getTimestamp().getMillis())/1000;
	}
}
