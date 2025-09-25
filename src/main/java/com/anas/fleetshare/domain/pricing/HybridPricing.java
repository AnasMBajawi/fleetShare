package com.anas.fleetshare.domain.pricing;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.user.MembershipTier;

import java.math.BigDecimal;

public class HybridPricing implements PricingStrategy{

    private final Money perMinute;
    private final Money perKm;

    public HybridPricing(Money perMinute, Money perKm) {
        this.perMinute = perMinute;
        this.perKm = perKm;
    }

    @Override
    public Money price(TripInfo info, MembershipTier tier) {
        double km = info.getDistance();
        if (km < 0.1) {
            km = 0.1;
        }
        Money distancePrice = new Money(
                perKm.getAmount().multiply(BigDecimal.valueOf(km))
        );

        double minutes = info.getDuration().toMinutes();
        if (minutes < 1.0) {
            minutes = 1.0;
        }
        Money timePrice = new Money(
                perMinute.getAmount().multiply(BigDecimal.valueOf(minutes))
        );

        Money basePrice = distancePrice.plus(timePrice);
        return applyTierDiscount(tier, basePrice);
    }

    private Money applyTierDiscount(MembershipTier tier, Money base) {
        if (tier == MembershipTier.PREMIUM) {
            return base.multiplyBy(0.9);
        }
        return base;
    }
}
