package com.anas.fleetshare.application.exceptions;

public class VehicleNotFoundException extends TripServiceException{
    public VehicleNotFoundException(String vehicleId) {
        super("vehicle not found : " + vehicleId);
    }
}
