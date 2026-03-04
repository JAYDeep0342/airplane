package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private String paymentMethod;   // CARD / UPI / NET_BANKING

    private String paymentStatus;   // SUCCESS / FAILED

    private LocalDateTime paymentDateTime;

    private String transactionId;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}