package com.anas.fleetshare.domain.vehicle;

import java.util.UUID;

public abstract class  Vehicle {
    private final UUID id;
    private final String model;
    private VehicleState state;

    protected Vehicle(String model){
        this.id = UUID.randomUUID();
        this.model = model;
        this.state = VehicleState.AVAILABLE;
    }

    public UUID getId() {return id; }
    public String getModel() { return model; }
    public VehicleState getState() { return state; }

    public void markInUse(){
        if (this.state == VehicleState.IN_USE) {
            throw new VehicleAlreadyInUseException(
                    "Vehicle " + id + " is already in use."
            );
        }
        this.state = VehicleState.IN_USE;
    }

    public void markAvailable(){
        if (this.state == VehicleState.AVAILABLE) {
            throw new VehicleAlreadyAvailableException(
                    "Vehicle " + id + " is already marked as AVAILABLE."
            );
        }
        this.state = VehicleState.AVAILABLE;
    }

    public void markInMaintenance(){
        this.state = VehicleState.MAINTENANCE;
    }
}
