package com.lld.practice.rules;

import com.lld.practice.props.PricingRuleProps;

import java.math.BigDecimal;

public class RushHourRule implements PricingRule {

    private final PricingRuleProps pricingRuleProps;

    public RushHourRule(PricingRuleProps pricingRuleProps) {
        this.pricingRuleProps = pricingRuleProps;
    }

    @Override
    public BigDecimal apply(BigDecimal fee) {
        return fee.add(pricingRuleProps.rushHourSurge());
    }
}
