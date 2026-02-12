package ips.airplanereservation.dto;

import lombok.Data;

import java.time.LocalTime;
@Data
public class FlightDto {
 private String FlightNumber ;
    private String airlineName;
    private String departureAirport;
    private String arrivalAirport;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
}

