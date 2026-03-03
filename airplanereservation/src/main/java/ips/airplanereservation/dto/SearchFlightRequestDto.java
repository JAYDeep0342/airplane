package ips.airplanereservation.dto;

import lombok.Data;

@Data
public class SearchFlightRequestDto {
    private String from ;
    private String to;
    private String dayOfOperation;
}