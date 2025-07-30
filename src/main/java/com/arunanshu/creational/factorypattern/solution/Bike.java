package com.arunanshu.creational.factorypattern.solution;

public class Bike implements Transport{
    @Override
    public void deliver() {
        System.out.println("deliver by bike");
    }
}
