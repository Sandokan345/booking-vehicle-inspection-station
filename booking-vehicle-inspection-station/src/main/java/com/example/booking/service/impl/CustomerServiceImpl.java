package com.example.booking.service.impl;

import com.example.booking.ModelMapper.MappingHelper;
import com.example.booking.dto.CustomerDTO;
import com.example.booking.exception.BookingException;
import com.example.booking.exception.ExceptionDescription;
import com.example.booking.model.Customer;
import com.example.booking.model.Province;
import com.example.booking.repository.CustomerRepository;
import com.example.booking.repository.ProvinceRepository;
import com.example.booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    private void validate(CustomerDTO customerDTO){
        if (customerDTO.getFirstName() == null){
            throw new BookingException(ExceptionDescription.CUSTOMER_FIRST_NAME_NOT_FOUND.getDescription());
        }
        if (customerDTO.getLastName() == null){
            throw new BookingException(ExceptionDescription.CUSTOMER_LAST_NAME_NOT_FOUND.getDescription());
        }
        if (customerDTO.getId() <0){
            throw new BookingException(ExceptionDescription.CUSTOMER_ID_NOT_FOUND.getDescription());
        }
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customerList = customerRepository.findAll();
        return MappingHelper.mapList(customerList, CustomerDTO.class);
    }

    @Override
    public Long save(CustomerDTO customerDTO) {
        validate(customerDTO);
        Customer customer = MappingHelper.map(customerDTO, Customer.class);
        return customerRepository.save(customer).getId();
    }

    @Override
    public void delete(Long id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
    }

    @Override
    public Long update(CustomerDTO customerDTO) {
        validate(customerDTO);
        Customer result =MappingHelper.map(customerDTO, Customer.class);
        return customerRepository.save(result).getId();
    }

    @Override
    public Customer getById(long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        return optionalCustomer.orElse(null);
    }
}
