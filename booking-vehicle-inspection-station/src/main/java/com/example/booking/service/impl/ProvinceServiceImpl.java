package com.example.booking.service.impl;

import com.example.booking.ModelMapper.MappingHelper;
import com.example.booking.dto.ProvinceDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.ExceptionDescription;
import com.example.booking.model.Province;
import com.example.booking.repository.ProvinceRepository;
import com.example.booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    private void validate(ProvinceDTO provinceDTO) {
        if (provinceDTO.getName() == null) {
            throw new BookingException(ExceptionDescription.PROVINCE_NAME_NOT_FOUND.getDescription());
        }
        if (provinceDTO.getId() <0) {
            throw new BookingException(ExceptionDescription.PROVINCE_ID_NOT_FOUND.getDescription());
        }
    }

    @Override
    public List<ProvinceDTO> findAll() {
        List<Province> provinceList = provinceRepository.findAll();
        return MappingHelper.mapList(provinceList, ProvinceDTO.class);
    }

    @Override
    public Long save(ProvinceDTO provinceDTO) {
        validate(provinceDTO);
        Province province = MappingHelper.map(provinceDTO, Province.class);
        return provinceRepository.save(province).getId();
    }

    @Override
    public void delete(Long id) {
        Province province =getById(id);
        provinceRepository.delete(province);
    }

    @Override
    public Province getById(long id) {
        Optional<Province> optionalProvince = provinceRepository.findById(id);
        return optionalProvince.orElse(null);
    }

    @Override
    public Long update(ProvinceDTO provinceDTO) {
        validate(provinceDTO);
        Province result = MappingHelper.map(provinceDTO, Province.class);
        return provinceRepository.save(result).getId();
    }
}
