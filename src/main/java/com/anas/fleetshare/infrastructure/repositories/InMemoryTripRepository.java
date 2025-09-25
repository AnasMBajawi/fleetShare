package com.anas.fleetshare.infrastructure.repositories;

import com.anas.fleetshare.application.repositories.TripRepository;
import com.anas.fleetshare.domain.trip.Trip;
import com.anas.fleetshare.domain.trip.TripId;
import com.anas.fleetshare.domain.trip.TripState;
import com.anas.fleetshare.domain.user.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryTripRepository implements TripRepository {
    private final Map<TripId, Trip> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Trip> findById(TripId id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Trip> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Trip> findCompletedOn(LocalDate date) {
        return store.values().stream().
                filter(t -> t.getState() == TripState.COMPLETED && t.getEndedAt().isPresent())
                .filter(t -> LocalDate.from(t.getEndedAt().get()
                        .atZone(ZoneId.systemDefault()))
                        .equals(date))
                .toList();
    }

    @Override
    public void save(Trip trip) {
        store.put(trip.getId(), trip);
    }
}
