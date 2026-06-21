package com.lld.practice.stratergy.locator;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.service.ParkingSpotService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NearestParkingSpotLocator implements ParkingSpotLocator {

    private final ParkingSpotService parkingSpotService;

    public NearestParkingSpotLocator(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public ParkingSpot find(VehicleType vehicleType) {
        Optional<ParkingSpot> spot = parkingSpotService.getNextAvailableSpotByVehicleType(vehicleType);
        return spot.orElseThrow(() -> new IllegalStateException("No parking spot available for Vehicle Type: " + vehicleType));
    }
}