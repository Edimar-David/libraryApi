package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.dto.itemLibrary.BookResponseDTO;
import com.NovaStack.biblioteca.model.Book;
import com.NovaStack.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<BookResponseDTO> findByUser(User user);

    @Query("""
            Select b from Book b
            where 
            b.id = :id AND
            b.user = :user
            """)
    Book findBook(Long id, User user);
}
