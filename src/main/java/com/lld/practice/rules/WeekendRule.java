package com.lld.practice.rules;

import com.lld.practice.props.PricingRuleProps;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class WeekendRule implements PricingRule {

    private final PricingRuleProps pricingRuleProps;

    public WeekendRule(PricingRuleProps pricingRuleProps) {
        this.pricingRuleProps = pricingRuleProps;
    }

    @Override
    public BigDecimal apply(BigDecimal fee) {
        return fee.add(pricingRuleProps.weekendSurgeCharge());
    }
}
