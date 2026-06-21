package com.lld.practice;

import com.lld.practice.entity.Floor;
import com.lld.practice.entity.ParkingSpot;
import com.lld.practice.entity.Ticket;
import com.lld.practice.enums.SpotType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.*;

@Configuration
public class AppBeanConfig {

    @Bean("ticketComparator")
    public Comparator<Ticket> ticketComparator() {
        return Comparator.comparing(Ticket::getEntryTime).reversed();
    }

    @Bean("parkingSpotComparator")
    public Comparator<ParkingSpot> parkingSpotComparator() {
        return Comparator.comparing(ParkingSpot::getSequence);
    }

    @Bean("zoneId")
    public ZoneId currentZone(@Value("${server.zone}") String zone) {
        return ZoneId.of(zone);
    }

    @Bean("floorsById")
    public Map<String, Floor> floorsStorage() {
        return new HashMap<>();
    }

    @Bean("parkingSpotsById")
    public Map<String, ParkingSpot> parkingSpotStorage() {
        return new HashMap<>();
    }

    @Bean("ticketsById")
    public Map<String, Ticket> ticketStorage() {
        return new HashMap<>();
    }

    @Bean("availableSpots")
    public Map<SpotType, Set<ParkingSpot>> availableSpotsStorage() {
        Map<SpotType, Set<ParkingSpot>> map = new EnumMap<>(SpotType.class);
        for(SpotType spotType: SpotType.values()) {
            map.put(spotType, new TreeSet<>(parkingSpotComparator()));
        }
        return map;
    }

    @Bean("activeTickets")
    public Set<Ticket> activeTicketsStorage() {
        return new TreeSet<>(ticketComparator());
    }
}
