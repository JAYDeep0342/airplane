package ips.airplanereservation.controller;

import ips.airplanereservation.dto.FlightDto;
import ips.airplanereservation.dto.SearchFlightResponseDto;
import ips.airplanereservation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



}
