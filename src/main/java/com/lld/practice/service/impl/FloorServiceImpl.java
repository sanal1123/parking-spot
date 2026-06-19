package com.lld.practice.service.impl;

import com.lld.practice.entity.Floor;
import com.lld.practice.repository.FloorsRepository;
import com.lld.practice.service.FloorService;

import java.util.NoSuchElementException;

public class FloorServiceImpl implements FloorService {

    private final FloorsRepository floorsRepository;

    public FloorServiceImpl(FloorsRepository floorsRepository) {
        this.floorsRepository = floorsRepository;
    }

    @Override
    public Floor createFloor(Integer sequence) {
        Floor floor = new Floor();
        floor.setSequence(sequence);
        return floorsRepository.save(floor);
    }

    @Override
    public Floor getFloorById(String floorId) {
        return floorsRepository.getById(floorId)
                .orElseThrow(() -> new NoSuchElementException("Floor Not Found"));
    }
}
