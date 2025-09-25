package com.anas.fleetshare.domain.vehicle;

public class Scooter extends Vehicle{
    private int batteryPercentage;

    public Scooter(String model, int batteryPercent) {
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
