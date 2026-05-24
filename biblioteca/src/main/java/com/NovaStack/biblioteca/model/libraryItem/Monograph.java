package com.NovaStack.biblioteca.model.libraryItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Monograph extends LibraryItem{
    @Column(nullable = false)
    private String institution;

    private String course;
}
