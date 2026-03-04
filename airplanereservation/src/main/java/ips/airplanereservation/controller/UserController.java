package ips.airplanereservation.controller;

import ips.airplanereservation.dto.*;
import ips.airplanereservation.entity.Booking;
import ips.airplanereservation.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

   @GetMapping("/flight-search")
   public ResponseEntity<List<SearchFlightResponseDto>> searchFlight(
           @RequestParam String from,
           @RequestParam String to,
           @RequestParam String dayOfOperation
           )
   {
 return  ResponseEntity.ok(userService. searchFlight(from,to,dayOfOperation));

   }
    @PostMapping("/create-booking")
    public ResponseEntity<BookingResponseDto> createBooking(
            @Valid @RequestBody BookingRequestDto request) {

        return ResponseEntity.ok(
                userService.createBooking(request)
        );
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentResponseDto> makePayment(
            @RequestBody PaymentRequestDto request) {

        return ResponseEntity.ok(userService.makePayment(request));
    }

}
