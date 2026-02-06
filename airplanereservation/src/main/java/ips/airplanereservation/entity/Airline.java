package ips.airplanereservation.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "airlines")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private String airlineName;

    @Column(nullable = false, unique = true)
    private String code;
    private String email ;
    private LocalDate registrationDate;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL)
    private Set<Flight> flights;

    @OneToOne(mappedBy = "airline")
    private User user;
}