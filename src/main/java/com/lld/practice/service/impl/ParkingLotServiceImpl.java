package com.lld.practice.service.impl;


import com.lld.practice.entity.Floor;
import com.lld.practice.entity.Payment;
import com.lld.practice.entity.Ticket;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.enums.SpotType;
import com.lld.practice.enums.VehicleType;
import com.lld.practice.factory.RateCalculationFactory;
import com.lld.practice.service.FloorService;
import com.lld.practice.service.ParkingLotService;
import com.lld.practice.service.ParkingSpotService;
import com.lld.practice.service.TicketService;
import com.lld.practice.stratergy.locator.ParkingSpotLocator;
import com.lld.practice.stratergy.parkingfee.RateCalculator;

import java.math.BigDecimal;
import java.util.*;

public class ParkingLotServiceImpl implements ParkingLotService {
    private final FloorService floorService;
    private final ParkingSpotLocator parkingSpotLocator;
    private final RateCalculationFactory rateCalculationFactory;
    private final TicketService ticketService;
    private final ParkingSpotService parkingSpotService;

    public ParkingLotServiceImpl(
            FloorService floorService,
            ParkingSpotLocator parkingSpotLocator,
            RateCalculationFactory rateCalculationFactory,
            TicketService ticketService,
            ParkingSpotService parkingSpotService
    ) {
        this.floorService = floorService;
        this.parkingSpotLocator = parkingSpotLocator;
        this.rateCalculationFactory = rateCalculationFactory;
        this.ticketService = ticketService;
        this.parkingSpotService = parkingSpotService;
    }

    public Floor createFloor(Integer sequence) {
        return floorService.createFloor(sequence);
    }

    public ParkingSpot createParkingSpot(Integer sequence, SpotType spotType, String floorId) {
        return parkingSpotService.createParkingSpot(sequence, spotType, floorId);
    }

    public Ticket parkVehicle(String licensePlate, VehicleType vehicleType) {
        ParkingSpot spot = parkingSpotLocator.find(vehicleType);
        return ticketService.createTicket(licensePlate, vehicleType, spot);
    }

    public Payment unparkVehicle(String ticketId) {
        Ticket ticket = ticketService.closeTicket(ticketId);
        RateCalculator rateCalculator = rateCalculationFactory.getStrategy(ticket);
        BigDecimal parkingFee = rateCalculator.calculate(ticket);
        return new Payment(ticketId, parkingFee);
    }

    public Set<Ticket> getActiveTickets() {
        return ticketService.getActiveTickets();
    }
}
