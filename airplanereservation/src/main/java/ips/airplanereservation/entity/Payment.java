// 10. Payment Entity
package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private LocalDateTime paymentDateTime;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    private String paymentMethod; // Card, UPI, Net Banking

    @Column(unique = true)
    private String transactionId;

    private String paymentStatus; // Success, Failed, Pending
}