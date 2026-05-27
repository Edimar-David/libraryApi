package com.NovaStack.biblioteca.model;

import com.NovaStack.biblioteca.model.libraryItem.LibraryItem;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false, unique = true)
    String email;
    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<LibraryItem> libraryItems;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Client> clients;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Loan> loan;



    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    @Override
    public String toString() {
        return  email;
    }
}
