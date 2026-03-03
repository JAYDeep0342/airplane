package ips.airplanereservation.service;
import ips.airplanereservation.dto.BookingRequestDto;
import ips.airplanereservation.dto.BookingResponseDto;
import ips.airplanereservation.dto.FlightDto;
import ips.airplanereservation.dto.SearchFlightResponseDto;
import ips.airplanereservation.entity.Booking;
import ips.airplanereservation.entity.Flight;
import ips.airplanereservation.entity.Passenger;
import ips.airplanereservation.repository.BookingRepository;
import ips.airplanereservation.repository.FlightRepository;
import ips.airplanereservation.repository.PassengerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ModelMapper modelMapper;
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    private final PassengerRepository passengerRepository;


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

        // 2️⃣ Seat check
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
}

