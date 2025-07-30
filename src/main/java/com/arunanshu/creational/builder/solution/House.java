package com.arunanshu.creational.builder.solution;

public class House {

    private String roof;
    private String balcony;
    private String bathroom;
    private boolean hasGarden;
    private boolean hasSwimmingPool;

    private House(HouseBuilder builder){
        this.roof=builder.roof;

    }
}
public class HouseBuilder{
    private String roof;
    private String balcony;
    private String bathroom;
    private boolean hasGarden;
    private boolean hasSwimmingPool;

    public HouseBuilder(String roof,String bathroom){
        this.roof=roof;
        this.bathroom=bathroom;
    }

    public HouseBuilder setGarder(boolean hasGarden){
        this.hasGarden=hasGarden;
        return this;
    }
    public HouseBuilder hasSwimmingPool(boolean hasSwimmingPool){
        this.hasSwimmingPool=hasSwimmingPool;
        return this;
    }
    public House build(){
        return new House(this);
    }
}
