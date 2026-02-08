package ips.airplanereservation.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightRequestDto {

    private Long id;
    private Long airlineId;
    private String airlineName;

    private String flightNumber;
    private String aircraftModel;

    private Integer totalSeats;
    private Integer economySeats;
    private Integer businessSeats;

    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private String flightDuration;
    private String daysOfOperation;
}
