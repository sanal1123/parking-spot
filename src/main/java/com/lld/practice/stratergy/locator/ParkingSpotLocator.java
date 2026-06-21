package com.lld.practice.stratergy.locator;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.VehicleType;

public interface ParkingSpotLocator {
    ParkingSpot find(VehicleType vehicleType);
}
