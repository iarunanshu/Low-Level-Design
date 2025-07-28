package com.arunanshu.solid.openclose.problem;

public class Paymentprocessor {

    public void processpayment(String paymentmethod, int amount){
        if(paymentmethod.equals("credircard")){
            System.out.println("making payment via creditcard"+amount);
        } else if (paymentmethod.equals("debitcard")) {
            System.out.println(("making payment via debitcard"+ amount));
        } else if (paymentmethod.equals("upi")) {
            System.out.println("making payment via upi"+ amount);
        }else {
            throw new IllegalArgumentException("unsupported payment method");
        }
    }
}
