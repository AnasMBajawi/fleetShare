package com.anas.fleetshare.domain.vehicle;

public class EBike extends Vehicle{
    private int batteryPercentage;

    public EBike(String model, int batteryPercentage) {
        super(model);
        this.batteryPercentage = batteryPercentage;
    }

    public int getBatteryPercentage(){
        return batteryPercentage;
    }
    public void setBatteryPercentage(int batteryPercentage){
        this.batteryPercentage = batteryPercentage;
    }
}
