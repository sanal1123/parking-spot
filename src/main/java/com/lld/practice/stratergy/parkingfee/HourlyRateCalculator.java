package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.factory.PricingRulesFactory;
import com.lld.practice.props.FeeCalculationProps;

import java.math.BigDecimal;
import java.util.Map;

public class HourlyRateCalculator extends RateCalculator {

    private final FeeCalculationProps feeCalculationProps;

    public HourlyRateCalculator(
            Map<VehicleType, BigDecimal> hourlyBaseFee,
            PricingRulesFactory pricingRules,
            FeeCalculationProps feeCalculationProps
    ) {
        super(hourlyBaseFee, pricingRules);
        this.feeCalculationProps = feeCalculationProps;
    }

    @Override
    protected BigDecimal calculation(Ticket ticket) {
        long minutes = getParkingDurationMinutes(ticket);
        long hours = (long) Math.ceil((double) minutes / 60);
        return getBaseFare(ticket)
                .multiply(feeCalculationProps.hourlySubsequentMultiplier())
                .multiply(new BigDecimal(hours));
    }
}
