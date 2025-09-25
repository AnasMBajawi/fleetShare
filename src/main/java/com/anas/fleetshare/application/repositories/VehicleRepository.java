package com.anas.fleetshare.application.repositories;

import com.anas.fleetshare.domain.vehicle.Vehicle;
import com.anas.fleetshare.domain.vehicle.VehicleState;

import java.util.*;

public interface VehicleRepository {
    Optional<Vehicle> findById(UUID id);
    List<Vehicle> findAll();
    List<Vehicle> findByState(VehicleState state);
    void save(Vehicle vehicle);
}
