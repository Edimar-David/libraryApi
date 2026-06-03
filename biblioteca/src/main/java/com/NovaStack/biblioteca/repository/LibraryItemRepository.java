package com.NovaStack.biblioteca.repository;

import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import com.NovaStack.biblioteca.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryItemRepository extends JpaRepository<LibraryItem, Long> {
    List<LibraryItem> findByUser(User user);

    LibraryItem findByIdAndUser(Long id, User user);

    List<LibraryItem> findByUserAndIsBorrowed(User user, boolean isBorrowed);
}
