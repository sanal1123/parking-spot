package com.lld.practice.repository.impl;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;
import com.lld.practice.repository.ParkingSpotRepository;

import java.util.*;

public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {

    private final Map<String, ParkingSpot> parkingSpotsById;
    private final Map<String, Floor> floorsById;
    private final Map<SpotType, Set<ParkingSpot>> availableSpots;

    public ParkingSpotRepositoryImpl(
            Map<String, ParkingSpot> parkingSpotsById,
            Map<String, Floor> floorsById,
            Map<SpotType, Set<ParkingSpot>> availableSpots
    ) {
        this.parkingSpotsById = parkingSpotsById;
        this.floorsById = floorsById;
        this.availableSpots = availableSpots;
    }

    @Override
    public ParkingSpot save(ParkingSpot spot) {
        ParkingSpot existingSpot = parkingSpotsById.get(spot.getSpotId());
        return existingSpot == null ?
                persistNewParkingSpot(spot) :
                updateExistingParkingSpot(spot, existingSpot);
    }

    private ParkingSpot updateExistingParkingSpot(ParkingSpot newSpot, ParkingSpot existing) {
        existing.setSpotId(newSpot.getSpotId());
        existing.setSpotType(newSpot.getSpotType());
        existing.setFloor(newSpot.getFloor());
        existing.setTicket(newSpot.getTicket());
        existing.setSequence(newSpot.getSequence());
        return newSpot;
    }

    @Override
    public Optional<ParkingSpot> getNextAvailableSpotByTypes(List<SpotType> spotTypes) {
        for(SpotType spotType: spotTypes) {
            Set<ParkingSpot> spots = availableSpots.get(spotType);
            Optional<ParkingSpot> spot = spots.stream().findFirst();

            if(spot.isPresent()) {
                spots.remove(spot.get());
                return Optional.of(new ParkingSpot(spot.get()));
            }
        }
        return Optional.empty();
    }

    private ParkingSpot persistNewParkingSpot(ParkingSpot spot) {
        String spotId = spot.getSpotId() == null ? UUID.randomUUID().toString() : spot.getSpotId();
        spot.setSpotId(spotId);
        ParkingSpot newSpot = new ParkingSpot(spot);
        newSpot.setFloor(floorsById.get(spot.getFloor().getFloorId()));
        parkingSpotsById.put(spot.getSpotId(), newSpot);
        addToAvailableSpot(spot);
        return spot;
    }

    private void addToAvailableSpot(ParkingSpot spot) {
        Set<ParkingSpot> spots = availableSpots.get(spot.getSpotType());
        spots.add(spot);
    }
}
