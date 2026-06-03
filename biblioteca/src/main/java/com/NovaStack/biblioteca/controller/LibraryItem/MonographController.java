package com.NovaStack.biblioteca.controller.LibraryItem;


import com.NovaStack.biblioteca.dto.libraryitem.MonographRequestDTO;
import com.NovaStack.biblioteca.dto.libraryitem.MonographResponseDTO;
import com.NovaStack.biblioteca.service.libraryItem.MonographService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/monograph")
public class MonographController {

    @Autowired
    MonographService service;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody MonographRequestDTO requestDTO){
        MonographResponseDTO response = service.createMonograph(requestDTO);
        return ResponseEntity.ok().body(response);
    }
}
