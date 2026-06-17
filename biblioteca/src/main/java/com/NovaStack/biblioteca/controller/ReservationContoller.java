package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.dto.Reservation.ListReservationResponseDTO;
import com.NovaStack.biblioteca.dto.Reservation.ReservationRequestDTO;
import com.NovaStack.biblioteca.dto.Reservation.ReservationResponseDTO;
import com.NovaStack.biblioteca.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<ListReservationResponseDTO> getAll(){
        ListReservationResponseDTO response = service.getAll();
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> completedReservation(@RequestParam Boolean completeReservation, @PathVariable Long id){
        ReservationResponseDTO response = service.completedReservation(completeReservation, id);
        return ResponseEntity.ok().body(response);
    }
}
