package com.lld.practice.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "rate.calculation")
public record RateCalculationProps(
        BigDecimal hourlySubsequentMultiplier
) {
}
