package com.example.booking.controller;

import com.example.booking.dto.ProvinceDTO;
import com.example.booking.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/province")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/list")
    public List<ProvinceDTO> findAll(){
        return provinceService.findAll();
    }

    @PostMapping("/province")
    public void save(@RequestBody ProvinceDTO provinceDTO){
        provinceService.save(provinceDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        provinceService.delete(id);
    }

    @PutMapping("/province")
    public Long update(@RequestBody ProvinceDTO provinceDTO){
        return provinceService.save(provinceDTO);
    }
}
