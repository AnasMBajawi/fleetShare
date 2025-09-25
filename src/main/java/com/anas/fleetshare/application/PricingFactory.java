package com.anas.fleetshare.application;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.pricing.DistanceBasedPricing;
import com.anas.fleetshare.domain.pricing.HybridPricing;
import com.anas.fleetshare.domain.pricing.PricingStrategy;
import com.anas.fleetshare.domain.pricing.TimeBasedPricing;

import java.math.BigDecimal;

public class PricingFactory {
    public enum Type { TIME, DISTANCE, HYBRID }

    private final Money perMinute;
    private final Money perKm;

    public PricingFactory() {
        this.perMinute = new Money(BigDecimal.valueOf(0.70));
        this.perKm = new Money(BigDecimal.valueOf(0.90));
    }

    public PricingFactory(Money perMinute, Money perKm) {
        this.perMinute = perMinute;
        this.perKm = perKm;
    }

    public PricingStrategy create(Type type) {
        switch (type) {
            case TIME: return new TimeBasedPricing(perMinute);
            case DISTANCE: return new DistanceBasedPricing(perKm);
            case HYBRID: return new HybridPricing(perMinute, perKm);
            default: throw new IllegalArgumentException("Unknown pricing kind: " + type);
        }
    }
}
