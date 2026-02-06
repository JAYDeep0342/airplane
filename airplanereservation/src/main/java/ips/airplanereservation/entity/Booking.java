// 7. Booking Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_instance_id", nullable = false)
    private FlightInstance flightInstance;
    @Column(nullable = false)
    private LocalDateTime bookingDateTime;
    private String bookingStatus;
    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;
    private String paymentStatus;
    @Column(unique = true)
    private String pnrNumber;
    private Integer numberOfPassengers;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<Passenger> passengers;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<SeatAssignment> seatAssignments;

    @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
    private Payment payment;
}