package ips.airplanereservation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Data
@AllArgsConstructor
public class AdminRequestDto {
    private String name ;
    private String password ;
    private String username;
}
