package com.anas.fleetshare.domain.trip;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.pricing.TripInfo;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.UUID;

public class Trip {
    private final TripId id;
    private final UUID userId;
    private final UUID vehicleId;

    private final Instant startedAt;
    private Optional<Instant> endedAt = Optional.empty();

    private final double startOdometerKm;
    private OptionalDouble endOdometerKm = OptionalDouble.empty();

    private TripState state = TripState.ACTIVE;
    private Optional<Money> price = Optional.empty();

    public Trip(UUID userId, UUID vehicleId, double startOdometerKm) {
        this.id = TripId.newId();
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startedAt = Instant.now();
        this.startOdometerKm = startOdometerKm;
    }

    public void stop(double endOdometerKm) {
        if (state != TripState.ACTIVE) {
            throw new InvalidTripStateException("Trip not ACTIVE");
        }
        if (endOdometerKm < startOdometerKm) {
            throw new IllegalArgumentException("End odometer < start");
        }
        this.endOdometerKm = OptionalDouble.of(endOdometerKm);
        this.endedAt = Optional.of(Instant.now());
        this.state = TripState.COMPLETED;
    }

    public void cancel() {
        if (state != TripState.ACTIVE) {
            throw new InvalidTripStateException("Trip not ACTIVE");
        }
        this.state = TripState.CANCELLED;
        this.endedAt = Optional.of(Instant.now());
    }

    public void setPrice(Money price) {
        this.price = Optional.of(price);
    }

    public TripInfo toTripInfo() {
        double distance = 0.0;
        if (endedAt.isPresent() && endOdometerKm.isPresent()) {
            distance = endOdometerKm.getAsDouble() - startOdometerKm;
        }
        Duration duration = endedAt.isPresent()
                ? Duration.between(startedAt, endedAt.get())
                : Duration.ZERO;
        return new TripInfo( duration, distance);
    }

    public TripId getId() { return id; }
    public UUID getUserId() { return userId; }
    public UUID getVehicleId() { return vehicleId; }
    public Instant getStartedAt() { return startedAt; }
    public Optional<Instant> getEndedAt() { return endedAt; }
    public double getStartOdometerKm() { return startOdometerKm; }
    public OptionalDouble getEndOdometerKm() { return endOdometerKm; }
    public TripState getState() { return state; }
    public Optional<Money> getPrice() { return price; }
}
