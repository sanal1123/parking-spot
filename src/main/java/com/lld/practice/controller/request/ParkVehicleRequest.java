package com.lld.practice.controller.request;

import com.lld.practice.enums.VehicleType;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ParkVehicleRequest {
    private String licensePlate;
    private VehicleType vehicleType;
}
