package com.example.booking.service.impl;

import com.example.booking.ModelMapper.MappingHelper;
import com.example.booking.dto.RegisterDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.ExceptionDescription;
import com.example.booking.model.Customer;
import com.example.booking.model.Register;
import com.example.booking.repository.CustomerRepository;
import com.example.booking.repository.RegisterRepository;
import com.example.booking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private void validate(RegisterDTO registerDTO) {
        if (registerDTO.getId() <0) {
            throw new BookingException(ExceptionDescription.REGISTER_ID_NOT_FOUND.getDescription());
        }
        if (registerDTO.getCustomerId() == null || registerDTO.getCustomerId() <0) {
            throw new BookingException(ExceptionDescription.CUSTOMER_ID_NOT_FOUND.getDescription());
        }
        Optional<Customer> optionalCustomer = customerRepository.findById(registerDTO.getCustomerId());
        if (!optionalCustomer.isPresent()) {
            throw new BookingException(ExceptionDescription.CUSTOMER_NOT_FOUND.getDescription());
        }
    }

    @Override
    public List<RegisterDTO> findAll() {
        List<Register> registerList = registerRepository.findAll();
        return MappingHelper.mapList(registerList, RegisterDTO.class);
    }

    @Override
    public Long save(RegisterDTO registerDTO) {
        validate(registerDTO);
        Register register = MappingHelper.map(registerDTO, Register.class);
        return registerRepository.save(register).getId();
    }

    @Override
    public void delete(Long id) {
        Register register =getById(id);
        registerRepository.delete(register);
    }

    @Override
    public Long update(RegisterDTO registerDTO) {
        validate(registerDTO);
        Register result = MappingHelper.map(registerDTO, Register.class);
        return registerRepository.save(result).getId();
    }

    @Override
    public Register getById(long id) {
        Optional<Register> optionalRegister = registerRepository.findById(id);
        return optionalRegister.orElse(null);
    }
}
