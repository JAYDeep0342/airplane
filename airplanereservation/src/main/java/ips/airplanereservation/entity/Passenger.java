// 8. Passenger Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "passengers")
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String passportNumber;

    private String nationality;

    private String passengerType; // Adult, Child, Infant

    private String mealPreference;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL)
    private Set<SeatAssignment> seatAssignments;
}