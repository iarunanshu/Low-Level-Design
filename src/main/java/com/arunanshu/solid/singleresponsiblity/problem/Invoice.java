package com.arunanshu.solid.singleresponsiblity.problem;

public class Invoice {
    private int amount;

    public Invoice(int amount){
        this.amount=amount;
    }

    public void generateinvoice(){
        System.out.println("invoice is generated");
    }
    public void savetodb(){
        System.out.println("invoice is saved to db");
    }
    public void sendemail(){
        System.out.println("email is sent");
    }
}
