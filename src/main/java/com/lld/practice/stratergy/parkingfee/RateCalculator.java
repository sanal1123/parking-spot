package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.factory.BaseFeeFactory;
import com.lld.practice.factory.PricingRulesFactory;
import com.lld.practice.rules.PricingRule;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public abstract class RateCalculator {

    private final PricingRulesFactory pricingRulesFactory;
    private final BaseFeeFactory baseFeeFactory;

    protected RateCalculator(BaseFeeFactory baseFeeFactory, PricingRulesFactory pricingRulesFactory) {
        this.baseFeeFactory = baseFeeFactory;
        this.pricingRulesFactory = pricingRulesFactory;
    }

    public final BigDecimal calculate(Ticket ticket) {
        validateTicket(ticket);
        BigDecimal fee = calculation(ticket);
        List<PricingRule> pricingRules = pricingRulesFactory.rules(ticket);

        for (PricingRule rule : pricingRules) {
            fee = rule.apply(fee);
        }
        return fee;
    }

    protected abstract BigDecimal calculation(Ticket ticket);

    protected void validateTicket(Ticket ticket) {
        Instant exit = ticket.getExitTime();
        if (exit == null) {
            throw new IllegalStateException("Exit time not recorded!");
        }

        TicketStatus ticketStatus = ticket.getTicketStatus();
        if(!TicketStatus.CLOSED.equals(ticketStatus)) {
            throw new IllegalStateException("Invalid Ticket state: " + ticketStatus);
        }
    }

    protected BigDecimal getBaseFare(Ticket ticket) {
        return baseFeeFactory.perVehicleType(ticket.getVehicleType());
    }

    protected static long getParkingDurationMinutes(Ticket ticket) {
        Instant entry = ticket.getEntryTime();
        Instant exit = ticket.getExitTime();
        return Duration.between(entry, exit).toMinutes();
    }
}
