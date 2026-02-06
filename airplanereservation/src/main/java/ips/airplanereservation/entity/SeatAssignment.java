// 9. SeatAssignment Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "seat_assignments")
public class SeatAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_instance_id", nullable = false)
    private FlightInstance flightInstance;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private SeatConfiguration seat;

    private LocalDate assignmentDate;
}