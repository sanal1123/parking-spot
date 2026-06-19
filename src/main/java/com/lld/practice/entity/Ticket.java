package com.lld.practice.entity;

import com.lld.practice.enums.TicketStatus;
import com.lld.practice.enums.VehicleType;

import java.time.Instant;

public class Ticket {
    private String ticketId;
    private Instant exitTime;
    private Instant entryTime;
    private String licencePlate;
    private VehicleType vehicleType;
    private ParkingSpot parkingSpot;
    private TicketStatus ticketStatus;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setExitTime(Instant exitTime) {
        this.exitTime = exitTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public Instant getExitTime() {
        return exitTime;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }


    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public TicketStatus ticketStatus() {
        return ticketStatus;
    }

    @Override
    public String toString() {
        return "[" +
                " ticketId: " + ticketId +
                " ParkingSpot: " + parkingSpot +
                " vehicleType: " + vehicleType +
                " licencePlate: " + licencePlate +
                " entryTime: " + entryTime +
                " status: " + ticketStatus +
                " ]";
    }

    public void setEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
    }
}
