//package ips.airplanereservation.repository;
//
//import ips.airplanereservation.entity.Pricing;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface PricingRepository extends JpaRepository<Pricing, Long> {
//    Optional<Pricing> findByFlightIdAndSeatClass(Long flightId, String seatClass);
//
//}