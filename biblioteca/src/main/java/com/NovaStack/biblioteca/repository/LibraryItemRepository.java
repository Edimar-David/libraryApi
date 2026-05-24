package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.dto.LibraryItem.BookResponseDTO;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import com.NovaStack.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {
    List<BookResponseDTO> findByUser(User user);

    @Query("""
            Select b from LibraryItem b
            where 
            b.id = :id AND
            b.user = :user
            """)
    LibraryItem findBook(Long id, User user);
}
