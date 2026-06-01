package com.NovaStack.biblioteca.model.libraryItem;

import com.NovaStack.biblioteca.model.User;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Magazine extends LibraryItem{

    private Integer editionNumber;


    public Magazine(String name, String author, LocalDate releaseDate, User user, Integer editionNumber) {
        super(name, author, releaseDate, user);
        this.editionNumber = editionNumber;
    }

    public Magazine() {
    }

    @Override
    public String getType() {
        return "magazine";
    }

    public Integer getEditionNumber() {
        return editionNumber;
    }

    public void setEditionNumber(Integer editionNumber) {
        this.editionNumber = editionNumber;
    }
}
