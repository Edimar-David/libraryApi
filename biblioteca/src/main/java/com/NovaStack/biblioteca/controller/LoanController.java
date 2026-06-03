package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.dto.loan.LoanRequestDTO;
import com.NovaStack.biblioteca.dto.loan.LoanResponseDTO;
import com.NovaStack.biblioteca.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    getbyid
//    transform

}
