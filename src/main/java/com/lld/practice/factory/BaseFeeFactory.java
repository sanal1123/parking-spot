package com.lld.practice.factory;

import com.lld.practice.enums.VehicleType;

import java.math.BigDecimal;

public interface BaseFeeFactory {
    BigDecimal perVehicleType(VehicleType vehicleType);
}
