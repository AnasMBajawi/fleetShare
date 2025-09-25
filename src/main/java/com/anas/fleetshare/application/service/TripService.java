package com.anas.fleetshare.application.service;

import com.anas.fleetshare.application.PricingFactory;
import com.anas.fleetshare.application.exceptions.TripNotFoundException;
import com.anas.fleetshare.application.exceptions.UserNotFoundException;
import com.anas.fleetshare.application.exceptions.VehicleNotFoundException;
import com.anas.fleetshare.application.repositories.TripRepository;
import com.anas.fleetshare.application.repositories.UserRepository;
import com.anas.fleetshare.application.repositories.VehicleRepository;
import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.pricing.PricingStrategy;
import com.anas.fleetshare.domain.trip.Trip;
import com.anas.fleetshare.domain.trip.TripId;
import com.anas.fleetshare.domain.user.User;
import com.anas.fleetshare.domain.vehicle.Vehicle;

import java.util.UUID;

public class TripService {
    private final UserRepository users;
    private final VehicleRepository vehicles;
    private final TripRepository trips;
    private final PricingFactory pricingFactory;

    public TripService(UserRepository users, VehicleRepository vehicles, TripRepository trips, PricingFactory pricingFactory) {
        this.users = users;
        this.vehicles = vehicles;
        this.trips = trips;
        this.pricingFactory = pricingFactory;
    }

    public Trip startTrip(UUID userId, UUID vehicleId, double startOdometerKm){
        User user = users.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(userId.toString()));
        Vehicle vehicle = vehicles.findById(vehicleId)
                .orElseThrow(()-> new VehicleNotFoundException(vehicleId.toString()));
        Trip trip = new Trip(user.getId(), vehicle.getId(), startOdometerKm);

        user.startTrip(trip.getId());
        vehicle.markInUse();

        trips.save(trip);
        users.save(user);
        vehicles.save(vehicle);

        return trip;
    }

    public Money stopTrip (TripId tripId, double endOdometerKm, PricingFactory.Type type){
        Trip trip = trips.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException(tripId.toString()));
        User user = users.findById(trip.getUserId())
                .orElseThrow(()-> new UserNotFoundException(trip.getUserId().toString()));
        Vehicle vehicle = vehicles.findById(trip.getVehicleId())
                .orElseThrow(()-> new VehicleNotFoundException(trip.getVehicleId().toString()));

        trip.stop(endOdometerKm);

        PricingStrategy pricingStrategy = pricingFactory.create(type);
        Money price = pricingStrategy.price(trip.toTripInfo(), user.getTier());

        user.charge(price);
        user.endTrip();
        vehicle.markAvailable();

        trips.save(trip);
        users.save(user);
        vehicles.save(vehicle);

        return price;
    }
}
