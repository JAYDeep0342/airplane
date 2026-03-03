package ips.airplanereservation.service;
import ips.airplanereservation.dto.FlightDto;
import ips.airplanereservation.dto.SearchFlightResponseDto;
import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final FlightRepository flightRepository;

    public List<SearchFlightResponseDto> searchFlight(
            String from,
            String to,
            String dayOfOperation) {

        List<Flight> flights =
                flightRepository
                        .findByDepartureAirportIgnoreCaseAndArrivalAirportIgnoreCaseAndDaysOfOperationContainingIgnoreCase(
                                from, to, dayOfOperation);

        return flights.stream()
                .map(flight ->
                        modelMapper.map(flight, SearchFlightResponseDto.class)
                )
                .toList();
    }
}

