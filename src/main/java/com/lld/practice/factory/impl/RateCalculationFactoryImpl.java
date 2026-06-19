package com.lld.practice.factory.impl;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.RateTier;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.factory.RateCalculationFactory;
import com.lld.practice.props.RateTierProps;
import com.lld.practice.stratergy.parkingfee.RateCalculator;

public class RateCalculationFactoryImpl implements RateCalculationFactory {
    private final RateTierProps rateTierProps;
    private final RateCalculator hourlyRateCalculator;
    private final RateCalculator flatRateCalculator;

    public RateCalculationFactoryImpl(
            RateTierProps rateTierProps,
            RateCalculator hourlyRateCalculator,
            RateCalculator flatRateCalculator
    ) {
        this.rateTierProps = rateTierProps;
        this.hourlyRateCalculator = hourlyRateCalculator;
        this.flatRateCalculator = flatRateCalculator;
    }

    public RateCalculator getStrategy(Ticket ticket) {
        if(TicketStatus.PAID.equals(ticket.ticketStatus()))
            throw new IllegalStateException("Cannot process paid Ticket");
        if(!TicketStatus.CLOSED.equals(ticket.ticketStatus())) {
            throw new IllegalStateException("Ticket must be closed before calculating the rate.");
        }

        RateTier tier = rateTierProps.config().get(ticket.getVehicleType());

        switch (tier) {
            case HOURLY -> {
                return hourlyRateCalculator;
            }

            case FLAT -> {
                return flatRateCalculator;
            }

            default -> throw new IllegalStateException("Invalid Rate Tier");
        }
    }
}
