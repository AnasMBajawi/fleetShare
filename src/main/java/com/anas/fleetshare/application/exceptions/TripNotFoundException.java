package com.anas.fleetshare.application.exceptions;

public class TripNotFoundException extends TripServiceException{
    public TripNotFoundException(String tripId) {
        super("trip not found " + tripId);
    }
}
