package com.anas.fleetshare.application.service;

import com.anas.fleetshare.application.PricingFactory;
import com.anas.fleetshare.application.exceptions.UserNotFoundException;
import com.anas.fleetshare.application.repositories.UserRepository;
import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.trip.Trip;
import com.anas.fleetshare.domain.trip.TripId;
import com.anas.fleetshare.domain.user.MembershipTier;
import com.anas.fleetshare.domain.user.User;

import java.util.UUID;

public class UserService {

    private final UserRepository users;
    private final TripService tripService;

    public UserService(UserRepository users, TripService tripService) {
        this.users = users;
        this.tripService = tripService;
    }

    public User register(String name, MembershipTier tier, Money initialBalance) {
        User u = new User(name, tier, initialBalance);
        users.save(u);
        return u;
    }

    public Money getBalance(UUID userId) {
        return users.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()))
                .getBalance();
    }

    public MembershipTier getTier(UUID userId) {
        return users.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId.toString()))
                .getTier();
    }

    public Trip startTrip(UUID userId, UUID vehicleId, double startOdometerKm) {
        return tripService.startTrip(userId, vehicleId, startOdometerKm);
    }

    public Money stopTrip(TripId tripId, double endOdometerKm, PricingFactory.Type pricingKind) {
        return tripService.stopTrip(tripId, endOdometerKm, pricingKind);
    }
}

