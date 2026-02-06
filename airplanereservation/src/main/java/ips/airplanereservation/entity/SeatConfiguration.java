// 5. SeatConfiguration Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "seat_configurations")
public class SeatConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private String seatNumber; // 12A, 15F

    @Column(nullable = false)
    private String seatClass; // Economy, Business, First

    private String seatType; // Window, Aisle, Middle

    private Integer rowNumber;

    @OneToMany(mappedBy = "seat")
    private Set<SeatAssignment> seatAssignments;
}