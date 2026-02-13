package ips.airplanereservation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;
@Data
public class FlightUpdateDto {
    @NotBlank(message = "Departure airport is required")
    private String departureAirport;
    @NotBlank(message = "Arrival airport is required")
    private String arrivalAirport;
    @NotNull(message = "departure time airport is required")
    private LocalTime departureTime;
    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;


}
