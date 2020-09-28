package com.example.booking.repository;

import com.example.booking.model.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
}
