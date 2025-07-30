package com.arunanshu.creational.factorypattern.solution;

public class TransportService {
    public static void main(String[] args) {
        Transport vehicle=TransportFactory.createtransportation("car");
        vehicle.deliver();
    }
}
