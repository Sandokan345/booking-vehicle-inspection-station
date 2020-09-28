package com.example.booking.service.impl;

import com.example.booking.ModelMapper.MappingHelper;
import com.example.booking.dto.DistrictDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.ExceptionDescription;
import com.example.booking.model.District;
import com.example.booking.model.Province;
import com.example.booking.repository.DistrictRepository;
import com.example.booking.repository.ProvinceRepository;
import com.example.booking.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    private void validate(DistrictDTO districtDTO) {
        if (districtDTO.getName() == null) {
            throw new BookingException(ExceptionDescription.DISTRICT_NAME_NOT_FOUND.getDescription());
        }
        if (districtDTO.getId() <0) {
            throw new BookingException(ExceptionDescription.DISTRICT_ID_NOT_FOUND.getDescription());
        }
        if (districtDTO.getProvinceId() == null || districtDTO.getProvinceId() < 0){
            throw new BookingException(ExceptionDescription.PROVINCE_ID_NOT_FOUND.getDescription());
        }
        Optional<Province>optionalProvince = provinceRepository.findById(districtDTO.getProvinceId());
        if (!optionalProvince.isPresent()){
            throw new BookingException(ExceptionDescription.PROVINCE_NOT_FOUND.getDescription());
        }
    }

    @Override
    public List<DistrictDTO> findAll() {
        List<District> districtList = districtRepository.findAll();
        return MappingHelper.mapList(districtList, DistrictDTO.class);
    }

    @Override
    public Long save(DistrictDTO districtDTO) {
        validate(districtDTO);
        District district = MappingHelper.map(districtDTO, District.class);
        return districtRepository.save(district).getId();
    }

    @Override
    public void delete(Long id) {
        District district = getById(id);
        districtRepository.delete(district);
    }

    @Override
    public Long update(DistrictDTO districtDTO) {
        validate(districtDTO);
        District result =MappingHelper.map(districtDTO, District.class);
        return districtRepository.save(result).getId();
    }

    @Override
    public District getById(long id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);
        return optionalDistrict.orElse(null);    }
}
