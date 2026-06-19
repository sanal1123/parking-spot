package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.factory.PricingRulesFactory;
import com.lld.practice.rules.PricingRule;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class RateCalculator {

    private final Map<VehicleType, BigDecimal> baseFeePerVehicleType;
    private final PricingRulesFactory pricingRulesFactory;

    protected RateCalculator(Map<VehicleType, BigDecimal> baseFeePerVehicleType, PricingRulesFactory pricingRulesFactory) {
        this.baseFeePerVehicleType = Collections.unmodifiableMap(baseFeePerVehicleType);
        this.pricingRulesFactory = pricingRulesFactory;
    }

    public final BigDecimal calculate(Ticket ticket) {
        validateTicket(ticket);
        BigDecimal fee = calculation(ticket);
        List<PricingRule> pricingRules = pricingRulesFactory.rules(ticket);

        for(PricingRule rule: pricingRules) {
            fee = rule.apply(fee);
        }
        return fee;
    }

    protected abstract BigDecimal calculation(Ticket ticket);

    protected void validateTicket(Ticket ticket) {
        Instant exit = ticket.getExitTime();
        if(exit == null) {
            throw new IllegalStateException("Exit time not recorded!");
        }

        VehicleType vehicleType = ticket.getVehicleType();

        if(!baseFeePerVehicleType.containsKey(vehicleType)) {
            throw new IllegalStateException("Base fare not available for the spotType: " + vehicleType);
        }
    }

    protected BigDecimal getBaseFare(Ticket ticket) {
        VehicleType vehicleType = ticket.getVehicleType();
        return baseFeePerVehicleType.get(vehicleType);
    }

    protected static long getParkingDurationMinutes(Ticket ticket) {
        Instant entry = ticket.getEntryTime();
        Instant exit = ticket.getExitTime();
        return Duration.between(entry, exit).toMinutes();
    }
}
