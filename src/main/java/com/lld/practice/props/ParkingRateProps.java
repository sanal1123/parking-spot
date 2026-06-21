package com.lld.practice.props;

import com.lld.practice.enums.VehicleType;

import java.math.BigDecimal;
import java.util.Map;

public record ParkingRateProps(
        Map<VehicleType, BigDecimal> hourlyRateBaseFeeConfig,
        Map<VehicleType, BigDecimal> flatRateBaseFeeConfig
) {
}
