package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.dto.loan.ConcludeLoanDTO;
import com.NovaStack.biblioteca.dto.loan.LoanRequestDTO;
import com.NovaStack.biblioteca.dto.loan.LoanResponseDTO;
import com.NovaStack.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService service;

    @PostMapping()
    public ResponseEntity<LoanResponseDTO> createLoan(@RequestBody LoanRequestDTO request){

        LoanResponseDTO response = service.createLoan(request);

        return ResponseEntity.ok().body(response);
    }
    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll(){


        List<LoanResponseDTO> response = service.getAll();

        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LoanResponseDTO> concludeLoan(@PathVariable Long id,@RequestBody ConcludeLoanDTO request){

        LoanResponseDTO response = service.concludeLoan(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoan(@PathVariable Long id){
        String response = service.deleteLoan(id);

       return ResponseEntity.ok(response);
    }


}
