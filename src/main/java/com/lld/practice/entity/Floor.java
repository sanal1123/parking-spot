package com.lld.practice.entity;

import java.util.Set;

public class Floor {
    private String floorId;
    private Integer sequence;
    private Set<ParkingSpot> spots;

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getFloorId() {
        return floorId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public void setSpots(Set<ParkingSpot> spots) {
        this.spots = spots;
    }

    public Set<ParkingSpot> getSpots() {
        return spots;
    }
}
