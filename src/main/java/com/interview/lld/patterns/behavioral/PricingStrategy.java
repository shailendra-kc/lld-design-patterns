package com.interview.lld.patterns.behavioral;

import java.math.BigDecimal;

@FunctionalInterface
public interface PricingStrategy {
    BigDecimal calculate(BigDecimal basePrice);
}
