package com.anas.fleetshare.domain.trip;

public class InvalidTripStateException extends RuntimeException{
    public InvalidTripStateException(String s) {
        super(s);
    }
}
