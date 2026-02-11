package ips.airplanereservation.controller;

import ips.airplanereservation.dto.FlightRequestDto;
import ips.airplanereservation.dto.FlightResponseDto;
import ips.airplanereservation.service.AuthService;
import ips.airplanereservation.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @PreAuthorize("hasRole('AIRLINE_ADMIN')")
    @PostMapping("/createFlight")
    public ResponseEntity<FlightResponseDto> createFlight(
            @RequestBody FlightRequestDto flightDTO) {

        return ResponseEntity.ok(flightService.createFlight(flightDTO));
    }
    @PreAuthorize("hasAnyRole('AIRLINE_ADMIN','SUPER_ADMIN')")
    @DeleteMapping("/deleteFlight/{id}")
    public ResponseEntity<Void> deleteFlight( @PathVariable  Long id){
        flightService.deleteflightByid(id);
        return ResponseEntity.noContent().build();

    }
}