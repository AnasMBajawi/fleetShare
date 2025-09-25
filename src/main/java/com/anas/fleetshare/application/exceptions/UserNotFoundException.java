package com.anas.fleetshare.application.exceptions;

public class UserNotFoundException extends TripServiceException{
    public UserNotFoundException(String userId) {
        super("user not found : " + userId);
    }
}
