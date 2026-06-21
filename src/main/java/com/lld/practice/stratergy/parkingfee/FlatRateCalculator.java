package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.factory.BaseFeeFactory;
import com.lld.practice.factory.PricingRulesFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FlatRateCalculator extends RateCalculator {

    public FlatRateCalculator(BaseFeeFactory baseFeeFactory, PricingRulesFactory pricingRulesFactory) {
        super(baseFeeFactory, pricingRulesFactory);
    }

    @Override
    protected BigDecimal calculation(Ticket ticket) {
        return getBaseFare(ticket);
    }
}
