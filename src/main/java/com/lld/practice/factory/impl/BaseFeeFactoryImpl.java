package com.lld.practice.factory.impl;

import com.lld.practice.enums.RateTier;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.factory.BaseFeeFactory;
import com.lld.practice.props.ParkingRateProps;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class BaseFeeFactoryImpl implements BaseFeeFactory {

    private final ParkingRateProps parkingRateProps;

    public BaseFeeFactoryImpl(ParkingRateProps parkingRateProps) {
        this.parkingRateProps = parkingRateProps;
    }

    @Override
    public BigDecimal perVehicleType(VehicleType vehicleType) {
        RateTier rateTier = parkingRateProps.tierMapping().get(vehicleType);
        Map<VehicleType, BigDecimal> baseFeeConfig;
        switch (rateTier) {
            case FLAT -> baseFeeConfig = parkingRateProps.flatRateBaseFeeConfig();
            case HOURLY -> baseFeeConfig = parkingRateProps.hourlyRateBaseFeeConfig();
            default -> throw new IllegalStateException("Base Fee not available for vehicle type: " + vehicleType);
        }
        return baseFeeConfig.get(vehicleType);
    }
}
