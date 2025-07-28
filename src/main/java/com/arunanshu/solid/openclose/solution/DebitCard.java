package com.arunanshu.solid.openclose.solution;

import com.arunanshu.solid.openclose.problem.Paymentprocessor;

public class DebitCard implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("paying via debit card"+ amount);
    }
}
