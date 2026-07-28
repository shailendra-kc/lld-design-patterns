package com.interview.lld.patterns.behavioral;

import java.math.BigDecimal;
import java.util.Objects;

public final class CheckoutService {
    private final PricingStrategy pricingStrategy;

    public CheckoutService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = Objects.requireNonNull(pricingStrategy);
    }

    public BigDecimal finalPrice(BigDecimal basePrice) {
        if (basePrice.signum() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return pricingStrategy.calculate(basePrice);
    }
}
