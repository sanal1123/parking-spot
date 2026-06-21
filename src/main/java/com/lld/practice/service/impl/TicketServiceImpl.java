package com.lld.practice.service.impl;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.repository.TicketRepository;
import com.lld.practice.service.ParkingSpotService;
import com.lld.practice.service.TicketService;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Set;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ParkingSpotService parkingSpotService;

    public TicketServiceImpl(TicketRepository ticketRepository, ParkingSpotService parkingSpotService) {
        this.ticketRepository = ticketRepository;
        this.parkingSpotService = parkingSpotService;
    }

    @Override
    public Ticket createTicket(String licensePlate, VehicleType vehicleType, ParkingSpot parkingSpot) {
        Ticket ticket = ticketRepository.save(buildTicket(licensePlate, vehicleType, parkingSpot));
        parkingSpotService.assignTicketToParkingSpot(parkingSpot.getSpotId(), ticket.getTicketId());
        return ticket;
    }

    @Override
    public Ticket getTicket(String ticketId) {
        return ticketRepository.getById(ticketId)
                .orElseThrow(() -> new NoSuchElementException("Ticket Not Found"));
    }

    @Override
    public Ticket closeTicket(String ticketId) {
        Ticket ticket = getTicket(ticketId);
        TicketStatus ticketStatus = ticket.getTicketStatus();
        if(TicketStatus.CLOSED.equals(ticketStatus) || TicketStatus.PAID.equals(ticketStatus)) {
            throw new IllegalStateException("Ticket Already Processed!");
        }
        parkingSpotService.markSpotAvailable(ticket.getParkingSpot().getSpotId());
        ticket.setTicketStatus(TicketStatus.CLOSED);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Override
    public Set<Ticket> getActiveTickets() {
        return ticketRepository.getByStatus(TicketStatus.ACTIVE);
    }

    private Ticket buildTicket(String licensePlate, VehicleType vehicleType, ParkingSpot parkingSpot) {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(Instant.now());
        ticket.setLicencePlate(licensePlate);
        ticket.setVehicleType(vehicleType);
        ticket.setParkingSpot(parkingSpot);
        return ticket;
    }
}
