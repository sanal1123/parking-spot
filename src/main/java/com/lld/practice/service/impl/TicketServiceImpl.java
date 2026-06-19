package com.lld.practice.service.impl;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.repository.TicketRepository;
import com.lld.practice.service.TicketService;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.Set;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(String licensePlate, VehicleType vehicleType, ParkingSpot parkingSpot) {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(Instant.now());
        ticket.setLicencePlate(licensePlate);
        ticket.setVehicleType(vehicleType);
        ticket.setParkingSpot(parkingSpot);
        return ticketRepository.save(ticket);
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
        ticket.getParkingSpot().setTicket(null);
        ParkingSpot spotCopy = new ParkingSpot(ticket.getParkingSpot());
        ticket.setParkingSpot(spotCopy);
        ticketRepository.updateStatus(ticketId, TicketStatus.CLOSED);
        return ticket;
    }

    @Override
    public Set<Ticket> getActiveTickets() {
        return ticketRepository.getByStatus(TicketStatus.ACTIVE);
    }
}
