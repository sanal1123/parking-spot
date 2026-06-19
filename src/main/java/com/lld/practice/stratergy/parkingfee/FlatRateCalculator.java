package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.factory.PricingRulesFactory;

import java.math.BigDecimal;
import java.util.Map;


public class FlatRateCalculator extends RateCalculator {

    public FlatRateCalculator(Map<VehicleType, BigDecimal> flatRateBaseFee, PricingRulesFactory pricingRulesFactory) {
        super(flatRateBaseFee, pricingRulesFactory);
    }

    @Override
    protected BigDecimal calculation(Ticket ticket) {
        return getBaseFare(ticket);
    }
}
