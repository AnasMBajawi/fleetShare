package com.anas.fleetshare.domain.trip;

import java.util.Objects;
import java.util.UUID;

public class TripId {
    private final UUID id;

    public TripId(UUID id) {
        if(id ==  null){
            throw new IllegalArgumentException("id cant ben null");
        }
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public static TripId newId(){
        return new TripId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object i){
        if(this == i){
            return true;
        }
        if(!(i instanceof TripId)) return false;

        TripId tripId = (TripId) i;

        return id.equals(tripId.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
