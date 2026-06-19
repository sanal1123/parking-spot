package com.lld.practice.props;

import java.math.BigDecimal;
import java.time.LocalTime;

public record PricingRuleProps(
        boolean weekendRuleEnabled,
        boolean rushHourRuleEnabled,
        BigDecimal weekendSurgeCharge,
        BigDecimal rushHourSurge,
        LocalTime rushHourStart,
        LocalTime rushHourEnd
) {
}
