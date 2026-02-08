package ips.airplanereservation.controller;

import ips.airplanereservation.dto.FlightRequestDto;
import ips.airplanereservation.dto.FlightResponseDto;
import ips.airplanereservation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;  // ✅ Import add करें
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {
    private final AuthService authService;

    @PreAuthorize("hasRole('AIRLINE_ADMIN ')")
    @PostMapping("/createFlight")
    public ResponseEntity<FlightResponseDto> createFlight(
            @RequestBody FlightRequestDto flightDTO) {

        return ResponseEntity.ok(authService.createFlight(flightDTO));
    }
}