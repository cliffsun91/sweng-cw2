package com.acmetelecom.microtype;

public class TelephoneNumber {

	final String telephoneNumber;
	
	public TelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	public String getTelephoneNumberAsString(){
		return telephoneNumber;
	}
}
