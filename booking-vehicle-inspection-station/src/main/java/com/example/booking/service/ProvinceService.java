package com.example.booking.service;

import com.example.booking.dto.ProvinceDTO;
import com.example.booking.model.Province;

import java.util.List;

public interface ProvinceService {

    List<ProvinceDTO> findAll();

    Long save(ProvinceDTO provinceDTO);

    void delete(Long id);

    Long update(ProvinceDTO provinceDTO);

    Province getById(long id);
}
