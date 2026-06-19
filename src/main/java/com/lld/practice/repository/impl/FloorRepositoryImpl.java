package com.lld.practice.repository.impl;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;
import com.lld.practice.repository.FloorsRepository;
import jakarta.annotation.Nonnull;

import java.util.*;

public class FloorRepositoryImpl implements FloorsRepository {

    private final Map<String, Floor> floorsById = new HashMap<>();
    private final Map<SpotType, TreeSet<ParkingSpot>> availableSpots = new HashMap<>();
    private static final Comparator<ParkingSpot> comparator = Comparator.comparing(ParkingSpot::getSequence);

    @Override
    public Optional<Floor> getById(String floorId) {
        return Optional.ofNullable(floorsById.get(floorId));
    }

    @Override
    public Floor save(Floor floor) {
        floor.setFloorId(UUID.randomUUID().toString());
        floor.setSpots(getNewSet());
        return floorsById.put(floor.getFloorId(), floor);
    }

    @Override
    public ParkingSpot saveParkingSpot(String floorId, ParkingSpot spot) {
        Floor floor = getById(floorId)
                .orElseThrow(() -> new NoSuchElementException("Floor Not Found"));

        spot.setFloor(floor);
        spot.setSpotId(UUID.randomUUID().toString());

        Set<ParkingSpot> spots = floor.getSpots();
        spots.add(spot);
        addToAvailableSpot(spot);
        return null;
    }

    @Override
    public Optional<ParkingSpot> getNextAvailableSpotByTypes(List<SpotType> spotTypes) {
        for(SpotType spotType: spotTypes) {
            TreeSet<ParkingSpot> spots = availableSpots.get(spotType);
            if(!spots.isEmpty())
                return Optional.ofNullable(spots.removeFirst());
        }
        return Optional.empty();
    }

    private void addToAvailableSpot(ParkingSpot spot) {
        Set<ParkingSpot> spots = availableSpots.computeIfAbsent(
                spot.getSpotType(),
                k -> getNewSet()
        );
        spots.add(spot);
    }

    @Nonnull
    private static TreeSet<ParkingSpot> getNewSet() {
        return new TreeSet<>(comparator);
    }
}
