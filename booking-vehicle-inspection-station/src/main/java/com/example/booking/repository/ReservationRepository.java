package com.example.booking.repository;

import com.example.booking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findOneByDateTime(LocalDateTime dateTime);
    Reservation findOneByLicenseNumber(Long licenseNumber);
}
