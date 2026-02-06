package ips.airplanereservation.service;

import ips.airplanereservation.dto.*;
import ips.airplanereservation.entity.Airline;
import ips.airplanereservation.entity.User;
import ips.airplanereservation.entity.type.RoleType;
import ips.airplanereservation.repository.AirlineRepository;
import ips.airplanereservation.repository.UserRepository;
import ips.airplanereservation.security.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final AirlineRepository airlineRepository;

    public SignupResponseDto signup(SignupRequestDto dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .nationality(dto.getNationality())
                .registrationDate(LocalDate.now())
                .roles(Set.of(RoleType.USER))
                .build();

        userRepository.save(user);

        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    public LoginResponseDto login(LoginRequestDto dto) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = authUtil.generateAccessToken(user);


        return new LoginResponseDto(
                token,
                user.getId()
        );

    }
    @Transactional
    public AdminResponseDto CreateAdmin(AdminRequestDto dto) {

        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Admin already exists");
        }

        Airline airline = Airline.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .email(dto.getEmail())
                .airlineName(dto.getAirlineName())
                .registrationDate(LocalDate.now())
                .build();

        airlineRepository.save(airline);

        User admin = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .airline(airline)
                .registrationDate(LocalDate.now())
                .nationality(dto.getNationality())
                .build();

        admin.getRoles().add(RoleType.AIRLINE_ADMIN);

        userRepository.save(admin);

        return new AdminResponseDto(
                admin.getId(),
                admin.getUsername()
        );
    }
}
