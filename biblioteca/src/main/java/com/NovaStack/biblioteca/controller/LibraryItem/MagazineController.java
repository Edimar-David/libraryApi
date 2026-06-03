package com.NovaStack.biblioteca.controller.LibraryItem;


import com.NovaStack.biblioteca.dto.libraryitem.MagazineRequestDTO;
import com.NovaStack.biblioteca.dto.libraryitem.MagazineResponseDTO;
import com.NovaStack.biblioteca.service.libraryItem.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magazine")
public class MagazineController {

    @Autowired
    MagazineService service;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody MagazineRequestDTO requestDTO){
        MagazineResponseDTO response = service.createMagazine(requestDTO);
        return ResponseEntity.ok().body(response);
    }
}
