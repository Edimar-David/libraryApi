package com.NovaStack.biblioteca.controller;


import com.NovaStack.biblioteca.dto.itemLibrary.BookRequestDTO;
import com.NovaStack.biblioteca.dto.itemLibrary.BookResponseDTO;
import com.NovaStack.biblioteca.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/allBooks")
    public ResponseEntity<?> getAllBooks(){
        List<BookResponseDTO> response = service.getAllBooks();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id){
        try {
            BookResponseDTO response = service.getBookById(id);
            return ResponseEntity.ok().body(response);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
        }
    }

}
