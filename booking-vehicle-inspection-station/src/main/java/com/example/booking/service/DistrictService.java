package com.example.booking.service;

import com.example.booking.dto.DistrictDTO;
import com.example.booking.model.District;

import java.util.List;

public interface DistrictService {

    List<DistrictDTO> findAll();

    Long save(DistrictDTO districtDTO);

    void delete(Long id);

    Long update(DistrictDTO districtDTO);

    District getById(long id);
}
