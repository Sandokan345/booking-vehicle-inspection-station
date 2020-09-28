package com.example.booking.service.impl;

import com.example.booking.ModelMapper.MappingHelper;
import com.example.booking.dto.InspectionStationDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.ExceptionDescription;
import com.example.booking.model.District;
import com.example.booking.model.InspectionStation;
import com.example.booking.model.Province;
import com.example.booking.repository.DistrictRepository;
import com.example.booking.repository.InspectionStationRepository;
import com.example.booking.repository.ProvinceRepository;
import com.example.booking.service.InspectionStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InspectionStationServiceImpl implements InspectionStationService {

    @Autowired
    private InspectionStationRepository inspectionStationRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    private void validate(InspectionStationDTO inspectionStationDTO) {
        if (inspectionStationDTO.getName() == null) {
            throw new BookingException(ExceptionDescription.INSPECTION_STATION_NAME_NOT_FOUND.getDescription());
        }
        if (inspectionStationDTO.getId() < 0) {
            throw new BookingException(ExceptionDescription.INSPECTION_STATION_ID_NOT_FOUND.getDescription());
        }
        if (inspectionStationDTO.getDistrictId() == null || inspectionStationDTO.getDistrictId() < 0) {
            throw new BookingException(ExceptionDescription.DISTRICT_ID_NOT_FOUND.getDescription());
        }
        if (inspectionStationDTO.getVehicleCapacity() == null) {
            throw new BookingException(ExceptionDescription.VEHICLE_CAPACITY_NOT_FOUND.getDescription());
        }
        if (inspectionStationDTO.getVehicleCapacity() != 10 && inspectionStationDTO.getVehicleCapacity() != 20) {
            throw new BookingException(ExceptionDescription.VEHICLE_CAPACITY_SHOULD_BE_10_OR_20.getDescription());
        }
        Optional<District> optionalDistrict = districtRepository.findById(inspectionStationDTO.getDistrictId());
        if (!optionalDistrict.isPresent()) {
            throw new BookingException(ExceptionDescription.DISTRICT_NOT_FOUND.getDescription());
        }
    }

    @Override
    public List<InspectionStationDTO> findAll() {
        List<InspectionStation> inspectionStationList = inspectionStationRepository.findAll();
        return MappingHelper.mapList(inspectionStationList, InspectionStationDTO.class);
    }

    @Override
    public Long save(InspectionStationDTO inspectionStationDTO) {
        validate(inspectionStationDTO);
        InspectionStation inspectionStation = MappingHelper.map(inspectionStationDTO, InspectionStation.class);
        return inspectionStationRepository.save(inspectionStation).getId();
    }

    @Override
    public void delete(Long id) {
        InspectionStation inspectionStation = getById(id);
        inspectionStationRepository.delete(inspectionStation);
    }

    @Override
    public Long update(InspectionStationDTO inspectionStationDTO) {
        validate(inspectionStationDTO);
        inspectionStationDTO.setVehicleCapacity(10);
        InspectionStation result =MappingHelper.map(inspectionStationDTO, InspectionStation.class);
        return inspectionStationRepository.save(result).getId();
    }

    @Override
    public InspectionStation getById(long id) {
        Optional<InspectionStation> optionalInspectionStation = inspectionStationRepository.findById(id);
        return optionalInspectionStation.orElse(null);
    }
}
