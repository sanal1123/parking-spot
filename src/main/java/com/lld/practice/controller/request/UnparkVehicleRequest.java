package com.lld.practice.controller.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UnparkVehicleRequest {
    private String ticketId;
}
