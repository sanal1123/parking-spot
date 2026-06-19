package com.lld.practice.rules;

import java.math.BigDecimal;

public interface PricingRule {
    BigDecimal apply(BigDecimal fee);
}
