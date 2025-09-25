package com.anas.fleetshare.domain.vehicle;

public class VehicleAlreadyAvailableException extends RuntimeException {
    public VehicleAlreadyAvailableException(String s) {
        super(s);
    }
}
