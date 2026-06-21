package com.lld.practice.repository.impl;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.SpotType;
import com.lld.practice.repository.ParkingSpotRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ParkingSpotRepositoryImpl implements ParkingSpotRepository {

    private final Map<String, ParkingSpot> parkingSpotsById;
    private final Map<String, Floor> floorsById;
    private final Map<SpotType, Set<ParkingSpot>> availableSpots;
    private final Map<String, Ticket> ticketsById;

    public ParkingSpotRepositoryImpl(
            Map<String, ParkingSpot> parkingSpotsById,
            Map<String, Floor> floorsById,
            Map<SpotType, Set<ParkingSpot>> availableSpots,
            Map<String, Ticket> ticketsById
    ) {
        this.parkingSpotsById = parkingSpotsById;
        this.floorsById = floorsById;
        this.availableSpots = availableSpots;
        this.ticketsById = ticketsById;
    }

    @Override
    public ParkingSpot save(ParkingSpot spot) {
        ParkingSpot existingSpot = parkingSpotsById.get(spot.getSpotId());
        return existingSpot == null ?
                persistNewParkingSpot(spot) :
                updateExistingParkingSpot(spot, existingSpot);
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

    @Override
    public void assignTicket(String spotId, String ticketId) {
        Ticket ticket = ticketsById.get(ticketId);
        ParkingSpot spot = parkingSpotsById.get(spotId);
        spot.setTicket(ticket);
    }

    @Override
    public void unAssignTicket(String spotId) {
        ParkingSpot spot = parkingSpotsById.get(spotId);
        spot.setTicket(null);
        availableSpots.get(spot.getSpotType()).add(spot);
    }

    private ParkingSpot updateExistingParkingSpot(ParkingSpot newSpot, ParkingSpot existing) {
        existing.setSpotId(newSpot.getSpotId());
        existing.setSpotType(newSpot.getSpotType());
        existing.setFloor(newSpot.getFloor());
        existing.setTicket(newSpot.getTicket());
        existing.setSequence(newSpot.getSequence());
        return newSpot;
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
