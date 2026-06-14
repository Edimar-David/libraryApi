package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.dto.Reservation.ReservationRequestDTO;
import com.NovaStack.biblioteca.dto.Reservation.ReservationResponseDTO;
import com.NovaStack.biblioteca.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationContoller {
    @Autowired
    ReservationService service;

    @PostMapping()
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO request){
        ReservationResponseDTO response = service.create(request);
        return ResponseEntity.ok().body(response);
    }
}
