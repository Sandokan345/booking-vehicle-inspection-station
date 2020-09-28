package com.example.booking.controller;

import com.example.booking.dto.DistrictDTO;
import com.example.booking.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/district")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @GetMapping("/list")
    public List<DistrictDTO> findAll(){
        return districtService.findAll();
    }

    @PostMapping("/district")
    public void save(@RequestBody DistrictDTO districtDTO){
        districtService.save(districtDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        districtService.delete(id);
    }

    @PutMapping("/district")
    public Long update(@RequestBody DistrictDTO districtDTO){
        return districtService.save(districtDTO);
    }
}
