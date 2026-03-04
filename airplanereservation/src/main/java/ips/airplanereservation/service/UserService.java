package ips.airplanereservation.service;
import ips.airplanereservation.dto.*;
import ips.airplanereservation.entity.Booking;
import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.entity.Passenger;
import ips.airplanereservation.entity.Payment;
import ips.airplanereservation.repository.BookingRepository;
import ips.airplanereservation.repository.FlightRepository;
import ips.airplanereservation.repository.PassengerRepository;
import ips.airplanereservation.repository.PaymentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;
    private final PaymentRepository paymentRepository;


    public List<SearchFlightResponseDto> searchFlight(
            String from,
            String to,
            String dayOfOperation) {

        List<Flight> flights =
                flightRepository
                        .findByDepartureAirportIgnoreCaseAndArrivalAirportIgnoreCaseAndDaysOfOperationContainingIgnoreCase(
                                from, to, dayOfOperation);

        return flights.stream()
                .map(flight ->
                        modelMapper.map(flight, SearchFlightResponseDto.class)
                )
                .toList();
    }

    public BookingResponseDto createBooking(BookingRequestDto request) {

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        if (flight.getTotalSeats() < request.getNumberOfSeats()) {
            throw new RuntimeException("Not enough seats available");
        }

        flight.setTotalSeats(
                flight.getTotalSeats() - request.getNumberOfSeats()
        );
        flightRepository.save(flight);

        Passenger passenger = new Passenger();
        passenger.setPassengerName(request.getPassengerName());
        passenger.setPassengerEmail(request.getPassengerEmail());

        Passenger savedPassenger = passengerRepository.save(passenger);

        double pricePerSeat = Double.parseDouble(
                flight.getPrice().replace("$", "")
        );

        double totalPrice = pricePerSeat * request.getNumberOfSeats();

        Booking booking = new Booking();
        booking.setNumberOfSeats(request.getNumberOfSeats());
        booking.setTotalPrice(totalPrice);
        booking.setBookingStatus("CONFIRMED");
        booking.setFlight(flight);
        booking.setPassenger(savedPassenger);

        Booking savedBooking = bookingRepository.save(booking);

        BookingResponseDto response = new BookingResponseDto();
        response.setBookingId(savedBooking.getId());
        response.setPassengerName(savedPassenger.getPassengerName());
        response.setPassengerEmail(savedPassenger.getPassengerEmail());
        response.setNumberOfSeats(savedBooking.getNumberOfSeats());
        response.setTotalPrice(savedBooking.getTotalPrice());
        response.setBookingStatus(savedBooking.getBookingStatus());
        response.setFlightNumber(flight.getFlightNumber());
        response.setDepartureAirport(flight.getDepartureAirport());
        response.setArrivalAirport(flight.getArrivalAirport());

        return response;
    }

    public PaymentResponseDto makePayment(PaymentRequestDto request) {

        Booking booking = bookingRepository.findById(request.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Payment payment = new Payment();
        payment.setAmount(booking.getTotalPrice());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setPaymentStatus("SUCCESS");
        payment.setPaymentDateTime(LocalDateTime.now());
        payment.setTransactionId("TXN" + System.currentTimeMillis());
        payment.setBooking(booking);

        Payment savedPayment = paymentRepository.save(payment);

        booking.setBookingStatus("CONFIRMED");
        bookingRepository.save(booking);

        PaymentResponseDto response = new PaymentResponseDto();
        response.setPaymentId(savedPayment.getId());
        response.setAmount(savedPayment.getAmount());
        response.setPaymentMethod(savedPayment.getPaymentMethod());
        response.setPaymentStatus(savedPayment.getPaymentStatus());
        response.setTransactionId(savedPayment.getTransactionId());

        return response;
    }

}

