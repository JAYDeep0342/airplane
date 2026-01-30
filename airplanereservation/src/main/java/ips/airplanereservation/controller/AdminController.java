package ips.airplanereservation.controller;

import ips.airplanereservation.dto.AdminRequestDto;
import ips.airplanereservation.dto.AdminResponseDto;
import ips.airplanereservation.repository.UserRepository;
import ips.airplanereservation.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private  final UserRepository userRepository;
    private  final AuthService authService ;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/createAdmin")
    public ResponseEntity<AdminResponseDto> CreateAdmin( @RequestBody AdminRequestDto dto ){

        return ResponseEntity.ok(authService.CreateAdmin(dto));
    }

}
