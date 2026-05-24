package com.NovaStack.biblioteca.model.libraryItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Media extends LibraryItem {
    @Column(nullable = false)
    private String mediaFormat;

    private Integer durationTime;
}
