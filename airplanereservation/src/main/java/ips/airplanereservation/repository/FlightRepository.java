package ips.airplanereservation.repository;

import ips.airplanereservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Object> findByFlightNumber(String flightNumber);


    List<Flight> findByDepartureAirportAndArrivalAirport(
            String departureAirport,
            String arrivalAirport
    );
}