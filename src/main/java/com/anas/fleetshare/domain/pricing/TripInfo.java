package com.anas.fleetshare.domain.pricing;

import java.time.Duration;

public class TripInfo {
    private final Duration duration;
    private final  double distance;

    public TripInfo(Duration duration, double distance) {
        this.duration = duration;
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public double getDistance() {
        return distance;
    }
}
