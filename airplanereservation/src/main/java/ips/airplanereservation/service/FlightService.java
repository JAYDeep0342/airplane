package ips.airplanereservation.service;

import ips.airplanereservation.dto.FlightDto;
import ips.airplanereservation.dto.FlightRequestDto;
import ips.airplanereservation.dto.FlightResponseDto;
import ips.airplanereservation.dto.FlightUpdateDto;
import ips.airplanereservation.entity.Airline;
import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.entity.FlightInstance;
import ips.airplanereservation.repository.AirlineRepository;
import ips.airplanereservation.repository.FlightRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FlightService {

    private final AirlineRepository airlineRepository;
    private final FlightRepository flightRepository;
    private final ModelMapper modelMapper;

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

    public List<FlightDto> findByDepartureCityAndArrivalCityAndDate(
            String from,
            String to) {

        List<Flight> flights =
                flightRepository.findByDepartureAirportAndArrivalAirport(from, to);

        return flights.stream()
                .map(flight -> {

                    FlightDto flightDto = modelMapper.map(flight, FlightDto.class);

                    flightDto.setAirlineName(flight.getAirline().getName());

                    return flightDto;
                })
                .toList();
    }

    public  FlightResponseDto updateFlight( Long id, FlightUpdateDto flightUpdateDto) {
   Flight flight = flightRepository.findById(id) .orElseThrow(()->  new RuntimeException("Flight not Found"));
   flight.setArrivalAirport(flightUpdateDto.getArrivalAirport());
   flight.setDepartureAirport(flightUpdateDto.getDepartureAirport());
   flight.setArrivalTime(flightUpdateDto.getArrivalTime());
   flight.setDepartureTime(flightUpdateDto.getDepartureTime());
   return new FlightResponseDto(flight.getId(),flight.getFlightNumber());
  }
}