//package ips.airplanereservation.repository;
//
//import ips.airplanereservation.entity.FlightInstance;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.time.LocalDate;
//import java.util.List;
//
//public interface FlightInstanceRepository extends JpaRepository<FlightInstance, Long> {
//    List<FlightInstance> searchFlights(String fromAirport, String toAirport, LocalDate date);
//
//}