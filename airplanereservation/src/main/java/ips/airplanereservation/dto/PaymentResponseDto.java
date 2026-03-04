package ips.airplanereservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

    private Long paymentId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;
}