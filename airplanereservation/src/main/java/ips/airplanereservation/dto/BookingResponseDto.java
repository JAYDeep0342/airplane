package ips.airplanereservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponseDto {

    private Long bookingId;

    private String passengerName;

    private String passengerEmail;

    private int numberOfSeats;

    private double totalPrice;

    private String bookingStatus;

    private String flightNumber;

    private String departureAirport;

    private String arrivalAirport;
}