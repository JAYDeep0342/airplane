package ips.airplanereservation.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private String username;
    private String password;
    private String name;
    private String address;
    private String email;
    private String nationality;
}
