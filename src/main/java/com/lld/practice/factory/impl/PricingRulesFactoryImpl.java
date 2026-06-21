package com.lld.practice.factory.impl;

import com.lld.practice.entity.Ticket;
import com.lld.practice.factory.PricingRulesFactory;
import com.lld.practice.props.PricingRuleProps;
import com.lld.practice.rules.PricingRule;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PricingRulesFactoryImpl implements PricingRulesFactory {
    private final PricingRuleProps pricingRuleProps;
    private final ZoneId zoneId;

    private final PricingRule weekendRule;
    private final PricingRule rushHourRule;

    public PricingRulesFactoryImpl(
            PricingRuleProps pricingRuleProps,
            ZoneId zoneId,
            PricingRule weekendRule,
            PricingRule rushHourRule
    ) {
        this.pricingRuleProps = pricingRuleProps;
        this.zoneId = zoneId;
        this.weekendRule = weekendRule;
        this.rushHourRule = rushHourRule;
    }

    @Override
    public List<PricingRule> rules(Ticket ticket) {
        List<PricingRule> rules = new ArrayList<>();

        if(pricingRuleProps.weekendRuleEnabled()) {
            DayOfWeek dayOfWeek = Instant.now().atZone(zoneId).getDayOfWeek();
            if (DayOfWeek.SATURDAY.equals(dayOfWeek) || DayOfWeek.SUNDAY.equals(dayOfWeek)) {
                rules.add(weekendRule);
            }
        }

        if(pricingRuleProps.rushHourRuleEnabled()) {
            LocalTime rushHourStart = pricingRuleProps.rushHourStart();
            LocalTime rushHourEnd = pricingRuleProps.rushHourEnd();

            LocalTime now = Instant.now()
                    .atZone(zoneId)
                    .toLocalTime();

            if(now.isAfter(rushHourStart) && now.isBefore(rushHourEnd))
                rules.add(rushHourRule);
        }

        return Collections.unmodifiableList(rules);
    }
}
