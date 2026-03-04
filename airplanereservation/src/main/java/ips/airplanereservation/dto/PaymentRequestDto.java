package ips.airplanereservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {

    private Long bookingId;
    private String paymentMethod;
}