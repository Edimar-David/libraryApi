package com.NovaStack.biblioteca.model.libraryItem;

import com.NovaStack.biblioteca.model.Loan;
import com.NovaStack.biblioteca.model.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy = JOINED)
public class LibraryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String author;

    private LocalDate releaseDate;

    private boolean isBorrowed;

    @OneToMany(mappedBy = "libraryItem", fetch = FetchType.LAZY)
    private List<Loan> loan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public LibraryItem(String name, String author, LocalDate releaseDate, User user) {

        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.isBorrowed = false;
        this.user = user;
    }

    public LibraryItem() {
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
