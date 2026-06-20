package com.lld.practice.repository.impl;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.repository.TicketRepository;

import java.util.*;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository {

    private final Map<String, Ticket> ticketsById;
    private final Set<Ticket> activeTickets;

    public TicketRepositoryImpl(Map<String, Ticket> ticketsById, Set<Ticket> activeTickets) {
        this.ticketsById = ticketsById;
        this.activeTickets = activeTickets;
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

        Ticket persist = new Ticket(ticket);
        ticketsById.put(ticketId, persist);
        activeTickets.add(persist);
        return ticket;
    }

    @Override
    public Optional<Ticket> getById(String ticketId) {
        return Optional.ofNullable(ticketsById.get(ticketId));
    }

    @Override
    public Set<Ticket> getByStatus(TicketStatus ticketStatus) {
        if(TicketStatus.ACTIVE.equals(ticketStatus))
            return activeTickets.stream()
                    .map(Ticket::new)
                    .collect(Collectors.toSet());
        return Set.of();
    }
}
