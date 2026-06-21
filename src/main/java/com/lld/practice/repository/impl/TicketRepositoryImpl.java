package com.lld.practice.repository.impl;

import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.repository.TicketRepository;

import java.util.*;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository {

    private final Map<String, Ticket> ticketsById;
    private final Set<Ticket> activeTickets;
    private final Map<String, ParkingSpot> parkingSpotsById;
    private final Comparator<Ticket> ticketComparator;

    public TicketRepositoryImpl(
            Map<String, Ticket> ticketsById,
            Set<Ticket> activeTickets,
            Map<String, ParkingSpot> parkingSpotsById,
            Comparator<Ticket> ticketComparator
    ) {
        this.ticketsById = ticketsById;
        this.activeTickets = activeTickets;
        this.parkingSpotsById = parkingSpotsById;
        this.ticketComparator = ticketComparator;
    }

    @Override
    public Ticket save(Ticket ticket) {
        String currentId = ticket.getTicketId();
        boolean hasId = currentId != null;
        if(hasId && ticketsById.containsKey(currentId)) {
            ticketsById.replace(currentId, ticket);
        }

        String ticketId = hasId ? currentId : UUID.randomUUID().toString();
        ticket.setTicketId(ticketId);

        Ticket persistTicket = new Ticket(ticket);
        ticketsById.put(ticketId, persistTicket);
        activeTickets.add(persistTicket);
        ParkingSpot persistedSpot = parkingSpotsById.get(ticket.getParkingSpot().getSpotId());
        persistedSpot.setTicket(persistTicket);
        return ticket;
    }

    @Override
    public Optional<Ticket> getById(String ticketId) {
        Ticket persistedTicket = ticketsById.get(ticketId);
        if(persistedTicket == null)
            return Optional.empty();
        return Optional.of(new Ticket(persistedTicket));
    }

    @Override
    public Set<Ticket> getByStatus(TicketStatus ticketStatus) {
        if(TicketStatus.ACTIVE.equals(ticketStatus))
            return activeTickets.stream()
                    .map(Ticket::new)
                    .collect(Collectors.toCollection(() ->
                                    new TreeSet<>(ticketComparator)));
        return Set.of();
    }
}
