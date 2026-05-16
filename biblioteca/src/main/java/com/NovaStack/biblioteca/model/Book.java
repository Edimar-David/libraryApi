package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.dto.book.BookRequestDTO;
import com.NovaStack.biblioteca.model.enums.BookCategory;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String author;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    private LocalDate releaseDate;

    private boolean isBorrowed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Book(BookRequestDTO requestDTO, User user) {

        this.name = requestDTO.name();
        this.author = requestDTO.author();
        this.category = BookCategory.fromString(requestDTO.category());
        this.releaseDate = requestDTO.releaseDate();
        this.isBorrowed = false;
        this.user = user;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
