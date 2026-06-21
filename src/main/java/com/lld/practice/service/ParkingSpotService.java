package com.lld.practice.service;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;
import com.lld.practice.enums.VehicleType;

import java.util.Optional;

public interface ParkingSpotService {
    ParkingSpot createParkingSpot(Integer sequence, SpotType spotType, String floorId);
    Optional<ParkingSpot> getNextAvailableSpotByVehicleType(VehicleType vehicleType);
    void markSpotAvailable(String spotId);
    void assignTicketToParkingSpot(String spotId, String ticketId);
}
