package com.lld.practice.repository.impl;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.repository.TicketRepository;

import java.util.*;

public class TicketRepositoryImpl implements TicketRepository {

    private static final Comparator<Ticket> ticketComparator = Comparator.comparing(Ticket::getEntryTime).reversed();

    private final Map<String, Ticket> ticketsById = new TreeMap<>();
    private final Set<Ticket> activeTickets = new TreeSet<>(ticketComparator);

    @Override
    public Ticket save(Ticket ticket) {
        String ticketId = UUID.randomUUID().toString();
        ticket.setTicketId(ticketId);
        ticketsById.put(ticket.getTicketId(), ticket);
        activeTickets.add(ticket);
        return ticket;
    }

    @Override
    public void updateStatus(String ticketId, TicketStatus ticketStatus) {
        Ticket ticket = ticketsById.get(ticketId);
        if(ticket == null) {
            throw new IllegalStateException("Ticket Not Found");
        }

        if(!TicketStatus.ACTIVE.equals(ticketStatus)) {
            activeTickets.remove(ticket);
        }
        ticket.setTicketStatus(ticketStatus);
    }

    @Override
    public Optional<Ticket> getById(String ticketId) {
        return Optional.ofNullable(ticketsById.get(ticketId));
    }

    @Override
    public Set<Ticket> getByStatus(TicketStatus ticketStatus) {
        if(TicketStatus.ACTIVE.equals(ticketStatus))
            return activeTickets;
        return Set.of();
    }
}
