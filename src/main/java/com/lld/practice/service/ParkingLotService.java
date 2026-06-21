package com.lld.practice.service;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Payment;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.SpotType;
import com.lld.practice.enums.VehicleType;

import java.util.Set;

public interface ParkingLotService {
    Floor createFloor(Integer sequence);
    ParkingSpot createParkingSpot(Integer sequence, SpotType spotType, String floorId);
    Ticket parkVehicle(String licensePlate, VehicleType vehicleType);
    Payment unparkVehicle(String ticketId);
    Set<Ticket> getActiveTickets();
}
