package com.anas.fleetshare.domain.user;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.trip.TripId;

import java.util.Optional;
import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
    private final MembershipTier tier;
    private Money balance;

    // Never null
    private Optional<TripId> activeTripId;

    // Single constructor – always initializes Optional
    public User(String name, MembershipTier tier, Money balance) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.tier = tier;
        this.balance = balance;
        this.activeTripId = Optional.empty();   // <<— important
    }

    // --- domain behavior ---
    public boolean hasActiveTrip() {
        return activeTripId.isPresent();        // safe (never-null Optional)
    }

    public void startTrip(TripId tripId) {
        if (hasActiveTrip()) {
            throw new IllegalStateException("User already has an active trip");
        }
        this.activeTripId = Optional.of(tripId);
    }

    public void endTrip() {
        this.activeTripId = Optional.empty();
    }

    public void charge(Money amount) {
        if (balance.isLessThan(amount)) {
            throw new InsufficientBalanceException(
                    "Insufficient balance. Required: " + amount + ", Available: " + balance
            );
        }
        balance = balance.minus(amount);
    }

    public void credit(Money amount) {
        balance = balance.plus(amount);
    }

    // --- getters ---
    public UUID getId() { return id; }
    public String getName() { return name; }
    public MembershipTier getTier() { return tier; }
    public Money getBalance() { return balance; }
    public Optional<TripId> getActiveTripId() { return activeTripId; }
}
