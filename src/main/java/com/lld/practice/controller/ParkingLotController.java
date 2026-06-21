package com.lld.practice.controller;

import com.lld.practice.controller.dto.ResponseDto;
import com.lld.practice.controller.request.FloorCreateRequest;
import com.lld.practice.controller.request.CreateParkingSpotRequest;
import com.lld.practice.controller.request.ParkVehicleRequest;
import com.lld.practice.controller.request.UnparkVehicleRequest;
import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Payment;
import com.lld.practice.entity.Ticket;
import com.lld.practice.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/parking-lot")
@RequiredArgsConstructor
public class ParkingLotController {
    private final ParkingLotService parkingLotService;

    @PostMapping("/floor")
    ResponseDto<Floor> createFloor(@RequestBody FloorCreateRequest request) {
        Floor floor = parkingLotService.createFloor(request.getSequence());
        return ResponseDto.<Floor>builder()
                .data(List.of(floor))
                .build();
    }

    @PostMapping("/floor/parking-spot")
    ResponseDto<ParkingSpot> createParkingSpot(@RequestBody CreateParkingSpotRequest request) {
        ParkingSpot spot = parkingLotService.createParkingSpot(request.getSequence(), request.getSpotType(), request.getFloorId());
        return ResponseDto.<ParkingSpot>builder()
                .data(List.of(spot))
                .build();
    }

    @PostMapping("/park-vehicle")
    ResponseDto<Ticket> parkVehicle(@RequestBody ParkVehicleRequest request) {
        Ticket ticket = parkingLotService.parkVehicle(request.getLicensePlate(), request.getVehicleType());
        return ResponseDto.<Ticket>builder()
                .data(List.of(ticket))
                .build();
    }

    @PostMapping("/unpark-vehicle")
    ResponseDto<Payment> unparkVehicle(@RequestBody UnparkVehicleRequest request) {
        Payment payment = parkingLotService.unparkVehicle(request.getTicketId());
        return ResponseDto.<Payment>builder()
                .data(List.of(payment))
                .build();
    }

    @GetMapping("/active-tickets")
    ResponseDto<Ticket> getActiveTickets() {
        Set<Ticket> tickets = parkingLotService.getActiveTickets();
        return ResponseDto.<Ticket>builder()
                .data(tickets)
                .build();
    }
}
