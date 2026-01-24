package ips.airplanereservation.controller;

import ips.airplanereservation.dto.SignupRequestDto;
import ips.airplanereservation.dto.SignupResponseDto;
import ips.airplanereservation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignupRequestDto dto) {
        System.out.println("🔥 SIGNUP CONTROLLER HIT");
        return ResponseEntity.ok(authService.signup(dto));
    }
}
