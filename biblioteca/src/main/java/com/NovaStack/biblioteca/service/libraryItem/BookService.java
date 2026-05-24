package com.NovaStack.biblioteca.service.libraryItem;

import com.NovaStack.biblioteca.dto.LibraryItem.BookRequestDTO;
import com.NovaStack.biblioteca.dto.LibraryItem.BookResponseDTO;
import com.NovaStack.biblioteca.model.libraryItem.Book;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.repository.LibraryItemRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    LibraryItemRepository libraryItemRepository;
    @Autowired
    UserRepository userRepository;


    public BookResponseDTO createBook(BookRequestDTO requestDTO) throws RuntimeException{
        User user = this.getUser();

        Book book = new Book(
                requestDTO.name(),
                requestDTO.author(),
                requestDTO.releaseDate(),
                user,
                requestDTO.category()
        );
        libraryItemRepository.save(book);

        return this.convertToResponse(book);
    }



    private BookResponseDTO convertToResponse(Book book){
        BookResponseDTO responseDTO = new BookResponseDTO(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.isBorrowed(),
                book.getCategory()
        );

        return responseDTO;
    }

    private User getUser() throws RuntimeException {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication == null){
                throw new NullPointerException("email is null");
            }

            String userEmail = authentication.getName();
            Optional<User> user = userRepository.findUserByEmail(userEmail);
            if(user.isPresent()){
                return user.get();
            }else {
                throw new RuntimeException("User not found");
            }
    }

//    public List<BookResponseDTO> getAllBooks() {
//        User user = this.getUser();
//        List<BookResponseDTO> response = libraryItemRepository.findByUser(user);
//
//        return response;
//    }
//
//
//    public BookResponseDTO getBookById(Long id) {
//        User user = this.getUser();
//        LibraryItem book = libraryItemRepository.findBook(id, user);
//        BookResponseDTO response = this.convertToResponse(book);
//
//        return response;
//    }
//
}
