package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.model.enums.LoanStatus;
import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    LocalDate loanDate;
    @Column(nullable = false)
    LocalDate dueDate;
    LocalDate returnDate;
    LoanStatus loanStatus;

    @ManyToOne
    @JoinColumn(name = "library_item_id", nullable = false)
    LibraryItem libraryItem;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Loan(LocalDate loanDate, LocalDate dueDate, LoanStatus loanStatus, LibraryItem libraryItem, Client client, User user) {
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.loanStatus = loanStatus;
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

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
