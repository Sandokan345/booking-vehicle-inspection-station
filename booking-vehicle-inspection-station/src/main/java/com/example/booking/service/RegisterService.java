package com.example.booking.service;

import com.example.booking.dto.RegisterDTO;
import com.example.booking.model.Register;

import java.util.List;

public interface RegisterService {

    List<RegisterDTO> findAll();

    Long save(RegisterDTO registerDTO);

    void delete(Long id);

    Long update(RegisterDTO registerDTO);

    Register getById(long id);
}
