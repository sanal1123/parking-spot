package com.lld.practice.entity;

import java.util.List;


public class Floor {
    private String floorId;
    private Integer sequence;
    private List<ParkingSpot> spots;

    public Floor() {}

    public Floor(Floor floor) {
        this.floorId = floor.getFloorId();
        this.sequence = floor.getSequence();
        this.spots = floor.getSpots()
                .stream()
                .map(ParkingSpot::new)
                .toList();
    }

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

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }
}
