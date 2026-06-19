package com.lld.practice.factory;


import com.lld.practice.entity.Ticket;
import com.lld.practice.stratergy.parkingfee.RateCalculator;


public interface RateCalculationFactory {
    RateCalculator getStrategy(Ticket ticket);
}