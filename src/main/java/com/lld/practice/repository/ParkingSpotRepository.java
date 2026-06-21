package com.lld.practice.repository;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;

import java.util.List;
import java.util.Optional;

public interface ParkingSpotRepository {
    ParkingSpot save(ParkingSpot spot);
    Optional<ParkingSpot> getNextAvailableSpotByTypes(List<SpotType> spotTypes);
    void assignTicket(String spotId, String ticketId);
    void unAssignTicket(String spotId);
}
