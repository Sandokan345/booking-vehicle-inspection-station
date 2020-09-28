package com.example.booking.service;

import com.example.booking.dto.CustomerDTO;
import com.example.booking.model.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> findAll();

    Long save(CustomerDTO customerDTO);

    void delete(Long id);

    Long update(CustomerDTO customerDTO);

    Customer getById(long id);
}
