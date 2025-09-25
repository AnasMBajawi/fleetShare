package com.anas.fleetshare.domain.pricing;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.user.MembershipTier;

public class TimeBasedPricing implements PricingStrategy{
    private final Money perMinute;

    public TimeBasedPricing(Money perMinute) {
        this.perMinute = perMinute;
    }


    @Override
    public Money price(TripInfo info, MembershipTier tier) {
        long minutes = info.getDuration().toMinutes();
        if(minutes < 1){
            minutes = 1;
        }
        Money basePrice = new Money(perMinute.getAmount().multiply(java.math.BigDecimal.valueOf(minutes)));

        return applyTierDiscount(tier, basePrice);
    }

    private Money applyTierDiscount(MembershipTier tier, Money basePrice){
        if(tier == MembershipTier.PREMIUM){
            return basePrice.multiplyBy(0.7);
        }
        return basePrice;
    }
}
