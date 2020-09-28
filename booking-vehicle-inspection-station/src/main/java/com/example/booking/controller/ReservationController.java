package com.example.booking.controller;

import com.example.booking.dto.ReservationDTO;
import com.example.booking.response.ReservationMessageResponse;
import com.example.booking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/list")
    public List<ReservationDTO> findAll(){
        return reservationService.findAll();
    }

    @PostMapping("/reservation")
    public ReservationMessageResponse save(@RequestBody ReservationDTO reservationDTO){
        return reservationService.save(reservationDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        reservationService.delete(id);
    }

    @PutMapping("/reservation")
    public Long update(@RequestBody ReservationDTO reservationDTO){
        return reservationService.update(reservationDTO);
    }
}
