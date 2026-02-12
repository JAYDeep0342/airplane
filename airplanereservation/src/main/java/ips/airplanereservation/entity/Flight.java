package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @Column(nullable = false, unique = true)
    private String flightNumber;

    private String aircraftModel;

    private Integer totalSeats;

    private Integer economySeats;

    private Integer businessSeats;

    @Column(nullable = false)
    private String departureAirport;

    @Column(nullable = false)
    private String arrivalAirport;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private String flightDuration;

    private String daysOfOperation; // Mon,Tue,Wed

    private String status;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private Set<FlightInstance> flightInstances;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private Set<SeatConfiguration> seatConfigurations;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private Set<Pricing> pricings;
}