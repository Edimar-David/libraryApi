package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.dto.loan.LoanRequestDTO;
import com.NovaStack.biblioteca.dto.loan.LoanResponseDTO;
import com.NovaStack.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    get
    @GetMapping
    public ResponseEntity<List<LoanResponseDTO>> getAll(){

        List<LoanResponseDTO> response = service.getAll();

        return ResponseEntity.ok().body(response);
    }

//    getbyid
//    transform


}
