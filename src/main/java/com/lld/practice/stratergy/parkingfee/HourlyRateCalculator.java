package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.factory.BaseFeeFactory;
import com.lld.practice.factory.PricingRulesFactory;
import com.lld.practice.props.RateCalculationProps;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HourlyRateCalculator extends RateCalculator {

    private final RateCalculationProps rateCalculationProps;

    public HourlyRateCalculator(
            BaseFeeFactory baseFeeFactory,
            PricingRulesFactory pricingRules,
            RateCalculationProps rateCalculationProps
    ) {
        super(baseFeeFactory, pricingRules);
        this.rateCalculationProps = rateCalculationProps;
    }

    @Override
    protected BigDecimal calculation(Ticket ticket) {
        long minutes = getParkingDurationMinutes(ticket);
        long hours = (long) Math.ceil((double) minutes / 60);
        return getBaseFare(ticket)
                .multiply(rateCalculationProps.hourlySubsequentMultiplier())
                .multiply(new BigDecimal(hours));
    }
}
