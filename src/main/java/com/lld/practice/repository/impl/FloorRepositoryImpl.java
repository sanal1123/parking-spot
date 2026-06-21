package com.lld.practice.repository.impl;

import com.lld.practice.entity.Floor;
import com.lld.practice.repository.FloorsRepository;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FloorRepositoryImpl implements FloorsRepository {

    private final Map<String, Floor> floorsById = new HashMap<>();

    @Override
    public Optional<Floor> getById(String floorId) {
        return Optional.ofNullable(floorsById.get(floorId));
    }

    @Override
    public Floor save(Floor floor) {
        floor.setFloorId(UUID.randomUUID().toString());
        return floorsById.put(floor.getFloorId(), floor);
    }


}
