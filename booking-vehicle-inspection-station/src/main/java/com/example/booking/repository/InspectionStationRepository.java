package com.example.booking.repository;

import com.example.booking.model.InspectionStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionStationRepository extends JpaRepository<InspectionStation, Long> {
}
