// 6. Pricing Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pricing")
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false)
    private String seatClass; // Economy, Business, First

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    private LocalDate validFromDate;

    private LocalDate validToDate;
}