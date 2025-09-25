package com.anas.fleetshare.domain.vehicle;

public class Car extends Vehicle{
    private final String plateNumber;
    private final int seats;

    public Car(String model, String plateNumber, int seats) {
        super(model);
        this.plateNumber = plateNumber;
        this.seats = seats;
    }

    public String getPlateNumber(){
        return plateNumber;
    }
    public int getSeats(){
        return seats;
    }
}
