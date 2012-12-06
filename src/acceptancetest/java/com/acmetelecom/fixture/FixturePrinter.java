package com.acmetelecom.fixture;

import com.acmetelecom.printer.Printer;

public class FixturePrinter implements Printer{

	String print;
	
	public FixturePrinter() {
		print = "";
	}
	
	@Override
	public void printHeading(String name, String phoneNumber, String pricePlan) {
		String heading = "Bill For Customer:\n";
		heading += "Name:" + name + "  Number:" + phoneNumber + "  Tariff:" + pricePlan + "\n"; 
		print += heading;
	}

	@Override
	public void printItem(String time, String callee, String duration, String cost) {
		String item = " -- time:" + time + ", receiver:" + callee + ", callduration:" + duration + ", cost:" + cost + "\n";
		print += item;
	}

	@Override
	public void printTotal(String total) {
		String line = "-----------------------------\n";
		line += "total = " + total + "\n\n";
		print += line;
	}
	
	@Override
	public String getString() {
		return print;
	}

	public static FixturePrinter aStandardPrinter(){
		return new FixturePrinter();
	}

}
