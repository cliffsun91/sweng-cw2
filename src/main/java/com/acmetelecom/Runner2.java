package com.acmetelecom;

import org.joda.time.DateTime;

import com.acmetelecom.call.Call;

public class Runner2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Running...");
        BillingSystem billingSystem = new BillingSystem();
        
        Call call = new Call(DateTime.now(), new DateTime().plusHours(2), "447777765432", "447711111111");
        
        billingSystem.fullCompletedCall(call);
        
        billingSystem.callInitiated("447777765432", "447711111111");
        billingSystem.callCompleted("447777765432", "447711111111");        
        
        billingSystem.createCustomerBills();
    }
}