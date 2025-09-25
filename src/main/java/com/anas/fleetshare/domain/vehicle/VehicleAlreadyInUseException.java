package com.anas.fleetshare.domain.vehicle;

public class VehicleAlreadyInUseException extends RuntimeException{
    public VehicleAlreadyInUseException(String s) {
        super(s);
    }
}
