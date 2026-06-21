package com.lld.practice.factory.impl;

import com.lld.practice.enums.RateTier;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.factory.BaseFeeFactory;
import com.lld.practice.props.ParkingRateProps;
import com.lld.practice.props.RateTierProps;

import java.math.BigDecimal;
import java.util.Map;

public class BaseFeeFactoryImpl implements BaseFeeFactory {

    private final ParkingRateProps parkingRateProps;
    private final RateTierProps rateTierProps;

    public BaseFeeFactoryImpl(ParkingRateProps parkingRateProps, RateTierProps rateTierProps) {
        this.parkingRateProps = parkingRateProps;
        this.rateTierProps = rateTierProps;
    }

    @Override
    public BigDecimal perVehicleType(VehicleType vehicleType) {
        RateTier rateTier = rateTierProps.config().get(vehicleType);
        Map<VehicleType, BigDecimal> baseFeeConfig;
        switch (rateTier) {
            case FLAT -> baseFeeConfig = parkingRateProps.flatRateBaseFeeConfig();
            case HOURLY -> baseFeeConfig = parkingRateProps.hourlyRateBaseFeeConfig();
            default -> throw new IllegalStateException("Base Fee not available for vehicle type: " + vehicleType);
        }
        return baseFeeConfig.get(vehicleType);
    }
}
