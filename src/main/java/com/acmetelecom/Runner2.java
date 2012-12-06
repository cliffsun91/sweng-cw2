package com.acmetelecom;

import org.joda.time.DateTime;

public class Runner2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Running...");
        BillingSystem billingSystem = new BillingSystem();
        
        billingSystem.callInitiated("447777765432", "447711111111");
        billingSystem.callCompleted("447777765432", new DateTime().plus(7200000));
        
        billingSystem.createCustomerBills();
    }
}