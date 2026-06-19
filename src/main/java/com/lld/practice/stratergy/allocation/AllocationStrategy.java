package com.lld.practice.stratergy.allocation;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.VehicleType;

public interface AllocationStrategy {
    ParkingSpot allocate(VehicleType vehicleType);
}
