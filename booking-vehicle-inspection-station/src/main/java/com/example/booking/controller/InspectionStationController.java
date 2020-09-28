package com.example.booking.controller;

import com.example.booking.dto.InspectionStationDTO;
import com.example.booking.service.InspectionStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inspection")
public class InspectionStationController {
    @Autowired
    private InspectionStationService inspectionStationService;

    @GetMapping("/list")
    public List<InspectionStationDTO> findAll(){
        return inspectionStationService.findAll();
    }

    @PostMapping("/inspection")
    public void save(@RequestBody InspectionStationDTO inspectionStationDTO){
        inspectionStationService.save(inspectionStationDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        inspectionStationService.delete(id);
    }

    @PutMapping("/inspection")
    public Long update(@RequestBody InspectionStationDTO inspectionStationDTO){
        return inspectionStationService.save(inspectionStationDTO);
    }
}
