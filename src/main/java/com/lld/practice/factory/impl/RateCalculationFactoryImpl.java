package com.lld.practice.factory.impl;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.RateTier;
import com.lld.practice.enums.TicketStatus;
import com.lld.practice.factory.RateCalculationFactory;
import com.lld.practice.props.ParkingRateProps;
import com.lld.practice.stratergy.parkingfee.RateCalculator;
import org.springframework.stereotype.Component;

@Component
public class RateCalculationFactoryImpl implements RateCalculationFactory {
    private final ParkingRateProps parkingRateProps;
    private final RateCalculator hourlyRateCalculator;
    private final RateCalculator flatRateCalculator;

    public RateCalculationFactoryImpl(
            ParkingRateProps parkingRateProps,
            RateCalculator hourlyRateCalculator,
            RateCalculator flatRateCalculator
    ) {
        this.parkingRateProps = parkingRateProps;
        this.hourlyRateCalculator = hourlyRateCalculator;
        this.flatRateCalculator = flatRateCalculator;
    }

    public RateCalculator getStrategy(Ticket ticket) {
        if(TicketStatus.PAID.equals(ticket.ticketStatus()))
            throw new IllegalStateException("Cannot process paid Ticket");
        if(!TicketStatus.CLOSED.equals(ticket.ticketStatus())) {
            throw new IllegalStateException("Ticket must be closed before calculating the rate.");
        }

        RateTier tier = parkingRateProps.tierMapping().get(ticket.getVehicleType());

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
