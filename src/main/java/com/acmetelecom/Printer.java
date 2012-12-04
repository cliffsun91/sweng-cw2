package com.acmetelecom;

public interface Printer {

    void createHeading(String name, String phoneNumber, String pricePlan);

    void createItem(String time, String callee, String duration, String cost);

    void createTotal(String total);
    
    String getString();
    
    void printAllToConsole();
}
