package com.lld.practice.factory;

import com.lld.practice.entity.Ticket;
import com.lld.practice.rules.PricingRule;

import java.util.List;

public interface PricingRulesFactory {
    List<PricingRule> rules(Ticket ticket);
}