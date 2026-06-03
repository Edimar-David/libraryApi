package com.NovaStack.biblioteca.controller.LibraryItem;


import com.NovaStack.biblioteca.dto.libraryitem.BookRequestDTO;
import com.NovaStack.biblioteca.dto.libraryitem.BookResponseDTO;
import com.NovaStack.biblioteca.service.libraryItem.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody BookRequestDTO requestDTO){
        BookResponseDTO response = service.createBook(requestDTO);
        return ResponseEntity.ok().body(response);
    }

}
