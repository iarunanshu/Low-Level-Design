package com.arunanshu.solid.singleresponsiblity.solution;

public class Invoice {

    private int amount;

    public Invoice(int amount){
        this.amount=amount;
    }

    public void invoicegenerate(){
        System.out.println("invoice is generated");
    }
}
