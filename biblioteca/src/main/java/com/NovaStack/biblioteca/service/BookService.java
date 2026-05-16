package com.NovaStack.biblioteca.service;

import com.NovaStack.biblioteca.dto.book.BookRequestDTO;
import com.NovaStack.biblioteca.dto.book.BookResponseDTO;
import com.NovaStack.biblioteca.model.Book;
import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.repository.BookRepository;
import com.NovaStack.biblioteca.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;

    public BookResponseDTO createBook(BookRequestDTO requestDTO) throws RuntimeException{
        User user = this.getUser();

        Book book = new Book(requestDTO, user);
        bookRepository.save(book);

        return this.convertToResponse(book);
    }

    public List<BookResponseDTO> getAllBooks() {
        User user = this.getUser();
        List<BookResponseDTO> response = bookRepository.findByUser(user);

        return response;
    }


    public BookResponseDTO getBookById(Long id) {
        User user = this.getUser();
        Book book = bookRepository.findBook(id, user);
        BookResponseDTO response = this.convertToResponse(book);

        return response;
    }


    private BookResponseDTO convertToResponse(Book book){
        BookResponseDTO responseDTO = new BookResponseDTO(
                book.getId(),
                book.getName(),
                book.getAuthor(),
                book.getCategory(),
                book.getReleaseDate(),
                book.isBorrowed()
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
}
