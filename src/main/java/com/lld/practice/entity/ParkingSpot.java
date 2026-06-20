package com.lld.practice.entity;

import com.lld.practice.enums.SpotType;



public class ParkingSpot {

    public ParkingSpot() {}

    public ParkingSpot(ParkingSpot parkingSpot) {
        this.spotId = parkingSpot.getSpotId();
        this.spotType = parkingSpot.getSpotType();
        this.floor = parkingSpot.getFloor();
        this.sequence = parkingSpot.getSequence();
        this.ticket = parkingSpot.getTicket();
    }

    private String spotId;
    private SpotType spotType;
    private Floor floor;
    private Ticket ticket;
    private Integer sequence;

    public String getSpotId() {
        return spotId;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public void setSpotType(SpotType spotType) {
        this.spotType = spotType;
    }

    public Floor getFloor() {
        return this.floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
         this.ticket = ticket;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "[SpotId: " + spotId + " SpotType: " + spotType + "]";
    }
}
