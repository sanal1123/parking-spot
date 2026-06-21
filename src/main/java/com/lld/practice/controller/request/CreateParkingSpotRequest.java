package com.lld.practice.controller.request;

import com.lld.practice.enums.SpotType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParkingSpotRequest {
    private Integer sequence;
    private SpotType spotType;
    private String floorId;
}
