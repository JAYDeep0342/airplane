// 4. FlightInstance Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "flight_instances")
public class FlightInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private LocalDate flightDate;

    private LocalDateTime actualDepartureTime;

    private LocalDateTime actualArrivalTime;

    private String gateNumber;

    private String status;

    private Integer availableEconomySeats;

    private Integer availableBusinessSeats;

    @OneToMany(mappedBy = "flightInstance", cascade = CascadeType.ALL)
    private Set<Booking> bookings;

    @OneToMany(mappedBy = "flightInstance", cascade = CascadeType.ALL)
    private Set<SeatAssignment> seatAssignments;
}