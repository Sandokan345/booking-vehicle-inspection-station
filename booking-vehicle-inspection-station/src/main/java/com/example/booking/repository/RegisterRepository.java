package com.example.booking.repository;

import com.example.booking.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
    Register findByCustomerId(Long customerId);
}
