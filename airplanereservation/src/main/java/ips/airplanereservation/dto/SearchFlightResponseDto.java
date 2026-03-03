package ips.airplanereservation.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchFlightResponseDto{

        private Long id;
        private String flightNumber;
        private String airlineName;
        private String departureAirport;
        private String arrivalAirport;
        private String departureTime;
        private String arrivalTime;
        private Integer totalSeats;
         private String price;
    }