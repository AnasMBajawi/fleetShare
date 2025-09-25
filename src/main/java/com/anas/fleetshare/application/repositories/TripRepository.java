package com.anas.fleetshare.application.repositories;

import com.anas.fleetshare.domain.trip.Trip;
import com.anas.fleetshare.domain.trip.TripId;

import java.time.LocalDate;
import java.util.*;

public interface TripRepository {
    Optional<Trip> findById(TripId id);
    List<Trip> findAll();
    List<Trip> findCompletedOn(LocalDate date);
    void save(Trip trip);
}
