package ips.airplanereservation.controller;

import ips.airplanereservation.dto.FlightDto;
import ips.airplanereservation.dto.FlightRequestDto;
import ips.airplanereservation.dto.FlightResponseDto;
import ips.airplanereservation.dto.FlightUpdateDto;
import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.service.AuthService;
import ips.airplanereservation.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasRole('AIRLINE_ADMIN')")
    @DeleteMapping("/deleteFlight/{id}")
    public ResponseEntity<Void> deleteFlight( @PathVariable  Long id){
        flightService.deleteflightByid(id);
        return ResponseEntity.noContent().build();

    }
    @PreAuthorize("hasAnyRole('AIRLINE_ADMIN','SUPER_ADMIN')")
    @GetMapping("/searchFlight")
    public ResponseEntity<List<FlightDto>> searchFlight(
            @RequestParam String from,
            @RequestParam String to){

        List<FlightDto> flights =
                flightService.findByDepartureCityAndArrivalCityAndDate(from, to);
        return ResponseEntity.ok(flights);
    }

    @PutMapping("/updateFlight/{id}")
    @PreAuthorize("hasRole('AIRLINE_ADMIN')")


    public ResponseEntity<FlightResponseDto>  updateFlight(@Valid @PathVariable Long id, @RequestBody FlightUpdateDto flightUpdateDto){
        return ResponseEntity.ok(flightService.updateFlight( id ,flightUpdateDto));
    }




}