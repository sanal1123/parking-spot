package com.lld.practice.props;

import com.lld.practice.enums.RateTier;
import com.lld.practice.enums.VehicleType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;
import java.util.Map;

@ConfigurationProperties(prefix = "rate")
public record ParkingRateProps(
        Map<VehicleType, BigDecimal> hourlyRateBaseFeeConfig,
        Map<VehicleType, BigDecimal> flatRateBaseFeeConfig,
        Map<VehicleType, RateTier> tierMapping
) {
}
