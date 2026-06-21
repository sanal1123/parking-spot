package com.lld.practice.service.impl;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.repository.FloorsRepository;
import com.lld.practice.repository.ParkingSpotRepository;
import com.lld.practice.service.ParkingSpotService;

import java.util.*;

public class ParkingSpotServiceImpl implements ParkingSpotService {

    private final ParkingSpotRepository parkingSpotRepository;
    private final FloorsRepository floorsRepository;
    private final Map<VehicleType, List<SpotType>> compatibleSpotTypes;

    public ParkingSpotServiceImpl(
            ParkingSpotRepository parkingSpotRepository,
            Map<VehicleType, List<SpotType>> compatibleSpotTypes,
            FloorsRepository floorsRepository
    ) {
        this.parkingSpotRepository = parkingSpotRepository;
        this.compatibleSpotTypes = compatibleSpotTypes;
        this.floorsRepository = floorsRepository;
    }

    @Override
    public ParkingSpot createParkingSpot(Integer sequence, SpotType spotType, String floorId) {
        Floor floor = floorsRepository.getById(floorId)
                .orElseThrow(() -> new NoSuchElementException("Floor Not Found"));
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setSequence(sequence);
        parkingSpot.setSpotType(spotType);
        parkingSpot.setFloor(floor);
        return parkingSpotRepository.save(parkingSpot);
    }

    @Override
    public Optional<ParkingSpot> getNextAvailableSpotByVehicleType(VehicleType vehicleType) {
        List<SpotType> spotTypes = compatibleSpotTypes.get(vehicleType);
        return parkingSpotRepository.getNextAvailableSpotByTypes(spotTypes);
    }

    @Override
    public void markSpotAvailable(String spotId) {
        parkingSpotRepository.unAssignTicket(spotId);
    }

    @Override
    public void assignTicketToParkingSpot(String spotId, String ticketId) {
        parkingSpotRepository.assignTicket(spotId, ticketId);
    }
}
