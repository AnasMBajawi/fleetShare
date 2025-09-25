package com.anas.fleetshare.domain.pricing;

import com.anas.fleetshare.domain.common.Money;
import com.anas.fleetshare.domain.user.MembershipTier;

public interface PricingStrategy {
    Money price(TripInfo info, MembershipTier tier);
}
