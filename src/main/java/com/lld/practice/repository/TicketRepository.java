package com.lld.practice.repository;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;

import java.util.Optional;
import java.util.Set;

public interface TicketRepository {
    Ticket save(Ticket ticket);
    void updateStatus(String ticketId, TicketStatus ticketStatus);
    Optional<Ticket> getById(String ticketId);
    Set<Ticket> getByStatus(TicketStatus ticketStatus);
}