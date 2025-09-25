package com.anas.fleetshare.application.service;

import com.anas.fleetshare.application.exceptions.VehicleNotFoundException;
import com.anas.fleetshare.application.repositories.VehicleRepository;
import com.anas.fleetshare.domain.vehicle.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class VehicleService {

    private final VehicleRepository vehicles;

    public VehicleService(VehicleRepository vehicles) {
        this.vehicles = vehicles;
    }

    public Car addCar(String model, String plateNumber, int seats) {
        Car c = new Car(model, plateNumber, seats);
        vehicles.save(c);
        return c;
    }

    public EBike addEBike(String model, int batteryPercent) {
        EBike b = new EBike(model, batteryPercent);
        vehicles.save(b);
        return b;
    }

    public Scooter addScooter(String model, int batteryPercent) {
        Scooter s = new Scooter(model, batteryPercent);
        vehicles.save(s);
        return s;
    }

    public List<Vehicle> listAll() {
        return vehicles.findAll();
    }

    public List<Vehicle> listByState(VehicleState state) {
        return vehicles.findByState(state);
    }

    public <T extends Vehicle> List<T> listByType(Class<T> type) {
        return vehicles.findAll().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    public <T extends Vehicle> List<T> listByTypeAndState(Class<T> type, VehicleState state) {
        return vehicles.findByState(state).stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toList());
    }

    public void markAvailable(UUID vehicleId) {
        Vehicle v = vehicles.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId.toString()));
        v.markAvailable();
        vehicles.save(v);
    }

    public void markInUse(UUID vehicleId) {
        Vehicle v = vehicles.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId.toString()));
        v.markInUse();
        vehicles.save(v);
    }

    public void markMaintenance(UUID vehicleId) {
        Vehicle v = vehicles.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException(vehicleId.toString()));
        v.markInMaintenance();
        vehicles.save(v);
    }
}

