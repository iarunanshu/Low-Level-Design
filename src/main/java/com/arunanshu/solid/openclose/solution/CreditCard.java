package com.arunanshu.solid.openclose.solution;

public class CreditCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("making payment via credit card"+amount);
    }
}
