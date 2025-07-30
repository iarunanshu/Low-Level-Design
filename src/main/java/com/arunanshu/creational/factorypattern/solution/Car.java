package com.arunanshu.creational.factorypattern.solution;

public class Car implements Transport{
    @Override
    public void deliver() {
        System.out.println("deliver by car");
    }
}
