package com.lld.practice.stratergy.parkingfee;

import com.lld.practice.entity.Ticket;
import com.lld.practice.factory.BaseFeeFactory;
import com.lld.practice.factory.PricingRulesFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FlatRateCalculator extends RateCalculator {

    public FlatRateCalculator(BaseFeeFactory baseFeeFactory, PricingRulesFactory pricingRulesFactory) {
        super(baseFeeFactory, pricingRulesFactory);
    }

    @Override
    protected BigDecimal calculation(Ticket ticket) {
        return getBaseFare(ticket);
    }
}
