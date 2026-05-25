package com.NovaStack.biblioteca.controller.LibraryItem;


import com.NovaStack.biblioteca.dto.LibraryItem.MagazineRequestDTO;
import com.NovaStack.biblioteca.dto.LibraryItem.MagazineResponseDTO;
import com.NovaStack.biblioteca.dto.LibraryItem.MediaRequestDTO;
import com.NovaStack.biblioteca.dto.LibraryItem.MediaResponseDTO;
import com.NovaStack.biblioteca.service.libraryItem.MagazineService;
import com.NovaStack.biblioteca.service.libraryItem.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    MediaService service;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody MediaRequestDTO requestDTO){
        MediaResponseDTO response = service.createMedia(requestDTO);
        return ResponseEntity.ok().body(response);
    }
}
