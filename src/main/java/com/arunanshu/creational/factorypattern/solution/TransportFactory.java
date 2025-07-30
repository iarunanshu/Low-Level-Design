package com.arunanshu.creational.factorypattern.solution;

public class TransportFactory {
    public static Transport createtransportation(String type){
        switch(type.toLowerCase()){
            case "bike":
                return new Bike();
            case "car":
                return new Car();
            case "bus":
                return new Bus();
            default:
                throw new IllegalArgumentException("unsupported type transport");
        }

    }
}
