package com.example.booking.controller;

import com.example.booking.dto.CustomerDTO;
import com.example.booking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public List<CustomerDTO> findAll(){
        return customerService.findAll();
    }

    @PostMapping("/customer")
    public void save(@RequestBody CustomerDTO customerDTO){
        customerService.save(customerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        customerService.delete(id);
    }

    @PutMapping("/customer")
    public Long update(@RequestBody CustomerDTO customerDTO){
        return customerService.save(customerDTO);
    }
}
