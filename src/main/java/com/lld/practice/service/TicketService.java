package com.lld.practice.service;

import com.lld.practice.entity.Ticket;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.VehicleType;

import java.util.Set;

public interface TicketService {
    Ticket createTicket(String licensePlate, VehicleType vehicleType, ParkingSpot parkingSpot);
    Ticket getTicket(String ticketId);
    Ticket closeTicket(String ticketId);
    Set<Ticket> getActiveTickets();
}
