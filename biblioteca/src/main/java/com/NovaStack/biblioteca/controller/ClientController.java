package com.NovaStack.biblioteca.controller;

import com.NovaStack.biblioteca.dto.client.ClientRequestDTO;
import com.NovaStack.biblioteca.dto.client.ClientResponseDTO;
import com.NovaStack.biblioteca.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService service;

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody  ClientRequestDTO dto){

        ClientResponseDTO response = service.createClient(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients(){

        List<ClientResponseDTO> response = service.getAllClients();
        return ResponseEntity.ok().body(response);
    }

//    @GetMapping("/${id}")
//    public ResponseEntity<?> getClientById(@PathVariable Long id){
//
//        ClientResponseDTO response = service.getClientById(id);
//        return ResponseEntity.ok().body(response);
//    }
}
