package com.NovaStack.biblioteca.controller.LibraryItem;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libraryItems")
public class LibraryItemController {

//    @Autowired
//    BookService service;
//
//    @GetMapping("/all")
//    public ResponseEntity<?> getAllBooks(){
//        List<BookResponseDTO> response = service.getAllBooks();
//        return ResponseEntity.ok().body(response);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getBookById(@PathVariable Long id){
//        try {
//            BookResponseDTO response = service.getBookById(id);
//            return ResponseEntity.ok().body(response);
//        } catch (NullPointerException e) {
//            System.out.println(e.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
//        }
//    }

}
