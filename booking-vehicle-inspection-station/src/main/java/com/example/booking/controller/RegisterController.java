package com.example.booking.controller;

import com.example.booking.dto.RegisterDTO;
import com.example.booking.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @GetMapping("/list")
    public List<RegisterDTO> findAll(){
        return registerService.findAll();
    }

    @PostMapping("/register")
    public void save(@RequestBody RegisterDTO registerDTO){
        registerService.save(registerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        registerService.delete(id);
    }

    @PutMapping("/register")
    public Long update(@RequestBody RegisterDTO registerDTO){
        return registerService.save(registerDTO);
    }
}
