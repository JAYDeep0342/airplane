package ips.airplanereservation.service;

import ips.airplanereservation.dto.FlightRequestDto;
import ips.airplanereservation.dto.FlightResponseDto;
import ips.airplanereservation.entity.Airline;
import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.repository.AirlineRepository;
import ips.airplanereservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
@Service
@RequiredArgsConstructor

public class FlightService {

    private final AirlineRepository airlineRepository;
    private final FlightRepository flightRepository;

    public FlightResponseDto createFlight(FlightRequestDto flightDto) {
        if (flightRepository.findByFlightNumber(flightDto.getFlightNumber()).isPresent()) {
            throw new RuntimeException("Flight already exists");
        }
        Airline airline = airlineRepository.findById(flightDto.getAirlineId())
                .orElseThrow(() -> new RuntimeException("Airline not found"));

        Flight flight = Flight.builder()
                .flightNumber(flightDto.getFlightNumber())
                .aircraftModel(flightDto.getAircraftModel())
                .totalSeats(flightDto.getTotalSeats())
                .economySeats(flightDto.getEconomySeats())
                .businessSeats(flightDto.getBusinessSeats())
                .departureAirport(flightDto.getDepartureAirport())
                .arrivalAirport(flightDto.getArrivalAirport())
                .departureTime(LocalTime.parse(flightDto.getDepartureTime()))
                .arrivalTime(LocalTime.parse(flightDto.getArrivalTime()))
                .flightDuration(flightDto.getFlightDuration())
                .daysOfOperation(flightDto.getDaysOfOperation())
                .status("ACTIVE")
                .airline(airline)
                .build();
        flightRepository.save(flight);
        return new FlightResponseDto(
                flight.getId(),
                flight.getFlightNumber()
        );

    }
    public void deleteflightByid(Long id) {
      Flight flight= flightRepository.findById(id).orElseThrow(() ->new RuntimeException("flight not found"));
      flightRepository.delete(flight);
    }
}