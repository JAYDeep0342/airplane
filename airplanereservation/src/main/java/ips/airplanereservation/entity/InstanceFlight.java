package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstanceFlight {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String flightNumber;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String destination;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    private LocalDate flightTime;

    private String departureTime;
    private String arrivalTime;

}

