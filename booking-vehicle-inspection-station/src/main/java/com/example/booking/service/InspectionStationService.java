package com.example.booking.service;

import com.example.booking.dto.InspectionStationDTO;
import com.example.booking.model.InspectionStation;

import java.util.List;

public interface InspectionStationService {

    List<InspectionStationDTO> findAll();

    Long save(InspectionStationDTO inspectionStationDTO);

    void delete(Long id);

    Long update(InspectionStationDTO inspectionStationDTO);

    InspectionStation getById(long id);
}
