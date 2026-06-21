package com.lld.practice.repository;

import com.lld.practice.entity.Floor;

import java.util.Optional;

public interface FloorsRepository {
    Optional<Floor> getById(String floorId);
    Floor save(Floor floor);
}