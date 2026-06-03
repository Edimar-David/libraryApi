package com.NovaStack.biblioteca.controller.LibraryItem;


import com.NovaStack.biblioteca.dto.LibraryItem.LibraryItemResponseDTO;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import com.NovaStack.biblioteca.service.libraryItem.LibraryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libraryItems")
public class LibraryItemController {

    @Autowired
    LibraryItemService service;

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        List<LibraryItemResponseDTO> response = service.findAll();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/notBorrowed")
    public ResponseEntity<?> getItemsNotBorrewed(){
        List<LibraryItem> response = service.getItemsNotBorrewed();
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable Long id){
        try {
            LibraryItemResponseDTO response = service.getItemById(id);
            return ResponseEntity.ok().body(response);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("item not found");
        }
    }

}
