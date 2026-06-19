package com.lld.practice.stratergy.allocation;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.service.ParkingSpotService;

import java.util.Optional;

public class NearestSpot implements AllocationStrategy {

    private final ParkingSpotService parkingSpotService;

    public NearestSpot(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public ParkingSpot allocate(VehicleType vehicleType) {
        Optional<ParkingSpot> spot = parkingSpotService.getNextAvailableSpotByVehicleType(vehicleType);
        return spot.orElseThrow(() -> new IllegalStateException("No parking spot available for Vehicle Type: " + vehicleType));
    }
}