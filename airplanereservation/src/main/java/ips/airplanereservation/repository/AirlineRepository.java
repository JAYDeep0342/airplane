package ips.airplanereservation.repository;

import ips.airplanereservation.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
    Optional<Airline> findByName(String name);

    Optional<Airline> findByCode(String code);

    boolean existsByName(String name);

    boolean existsByCode(String code);
}