package com.lld.practice.service.impl;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.repository.FloorsRepository;
import com.lld.practice.service.ParkingSpotService;

import java.util.*;

public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final FloorsRepository floorsRepository;
    private final Map<VehicleType, List<SpotType>> compatibleSpotTypes;

    public ParkingSpotServiceImpl(FloorsRepository floorsRepository, Map<VehicleType, List<SpotType>> compatibleSpotTypes) {
        this.floorsRepository = floorsRepository;
        this.compatibleSpotTypes = compatibleSpotTypes;
    }

    @Override
    public ParkingSpot createParkingSpot(Integer sequence, SpotType spotType, String floorId) {
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSequence(sequence);
        parkingSpot.setSpotType(spotType);
        return floorsRepository.saveParkingSpot(floorId, parkingSpot);
    }

    @Override
    public Optional<ParkingSpot> getNextAvailableSpotByVehicleType(VehicleType vehicleType) {
        List<SpotType> spotTypes = compatibleSpotTypes.get(vehicleType);
        return floorsRepository.getNextAvailableSpotByTypes(spotTypes);
    }
}
