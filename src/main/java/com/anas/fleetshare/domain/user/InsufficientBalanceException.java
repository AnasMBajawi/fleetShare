package com.anas.fleetshare.domain.user;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String s) {
        super(s);
    }
}
