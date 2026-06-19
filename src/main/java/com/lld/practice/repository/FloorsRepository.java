package com.lld.practice.repository;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;

import java.util.List;
import java.util.Optional;

public interface FloorsRepository {
    Optional<Floor> getById(String floorId);
    Floor save(Floor floor);
    ParkingSpot saveParkingSpot(String floorId, ParkingSpot spot);
    Optional<ParkingSpot> getNextAvailableSpotByTypes(List<SpotType> spotTypes);
}