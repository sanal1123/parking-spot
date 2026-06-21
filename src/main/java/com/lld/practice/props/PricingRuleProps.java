package com.lld.practice.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.time.LocalTime;

@ConfigurationProperties("rate.pricing-rule")
public record PricingRuleProps(
        boolean weekendRuleEnabled,
        boolean rushHourRuleEnabled,
        BigDecimal weekendSurgeCharge,
        BigDecimal rushHourSurge,
        LocalTime rushHourStart,
        LocalTime rushHourEnd
) {
}
