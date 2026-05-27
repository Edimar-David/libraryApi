package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    LocalDate loanDate;
    @Column(nullable = false)
    LocalDate dueDate;
    LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "library_item_id", nullable = false)
    LibraryItem libraryItem;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Loan(String name, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate, LibraryItem libraryItem, Client client, User user) {
        this.name = name;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.libraryItem = libraryItem;
        this.client = client;
        this.user = user;
    }

    public Loan() {
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

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
