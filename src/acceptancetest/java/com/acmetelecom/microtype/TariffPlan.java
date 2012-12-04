package com.acmetelecom.microtype;

public class TariffPlan {
	
	final String tariffCode;
	
	public TariffPlan(String tariffCode) {
		this.tariffCode = tariffCode;
	}
	
	public String getTariffCode(){
		return tariffCode;
	}
}
