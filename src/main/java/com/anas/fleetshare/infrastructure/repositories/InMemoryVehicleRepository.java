package com.anas.fleetshare.infrastructure.repositories;

import com.anas.fleetshare.application.repositories.VehicleRepository;
import com.anas.fleetshare.domain.vehicle.Vehicle;
import com.anas.fleetshare.domain.vehicle.VehicleState;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryVehicleRepository implements VehicleRepository {
    private final Map<UUID, Vehicle> store = new ConcurrentHashMap<>();

    @Override
    public Optional<Vehicle> findById(UUID id) {
        return Optional.
                ofNullable(store.get(id));
    }

    @Override
    public List<Vehicle> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Vehicle> findByState(VehicleState state) {
        return store.values().stream().filter(vehicle -> vehicle.getState() == state).collect(Collectors.toList());
    }

    @Override
    public void save(Vehicle vehicle) {
        store.put(vehicle.getId(), vehicle);
    }
}
