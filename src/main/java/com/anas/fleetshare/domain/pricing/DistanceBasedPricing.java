package com.anas.fleetshare.domain.pricing;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.user.MembershipTier;

import java.math.BigDecimal;

public class DistanceBasedPricing implements PricingStrategy {

    private final Money perKm;

    public DistanceBasedPricing(Money perKm) {
        this.perKm = perKm;
    }

    @Override
    public Money price(TripInfo info, MembershipTier tier) {
        double km = info.getDistance();

        if(km < 0.1){
            km = 0.1;
        }
        Money basePrice = new Money(perKm.getAmount().multiply(BigDecimal.valueOf(km)));

        return applyTierDiscount(tier, basePrice);
    }

    private Money applyTierDiscount(MembershipTier tier, Money basePrice) {
        if(tier == MembershipTier.PREMIUM){
            return basePrice.multiplyBy(0.7);
        }
        return basePrice;
    }
}
