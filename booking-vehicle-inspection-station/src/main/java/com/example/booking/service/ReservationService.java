package com.example.booking.service;

import com.example.booking.dto.ReservationDTO;
import com.example.booking.model.Reservation;
import com.example.booking.response.ReservationMessageResponse;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> findAll();

    void delete(Long id);

    Long update(ReservationDTO reservationDTO);

    Reservation getById(long id);

    ReservationMessageResponse save(ReservationDTO reservationDTO);
}
