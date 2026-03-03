package ips.airplanereservation.entity;

import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numberOfSeats;

    private double totalPrice;

    private String bookingStatus;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
}