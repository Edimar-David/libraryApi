package com.NovaStack.biblioteca.model.libraryItem;

import com.NovaStack.biblioteca.model.User;
import com.NovaStack.biblioteca.model.enums.BookCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Entity
public class Book extends LibraryItem{
    @Enumerated(EnumType.STRING)
    private BookCategory category;


    public Book(String name, String author, LocalDate releaseDate, User user, String category) {
        super(name, author, releaseDate, user);
        this.category = BookCategory.fromString(category);
    }

    public Book() {

    }


    @Override
    public String getType() {
        return "book";
    }


    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
