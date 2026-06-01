package com.NovaStack.biblioteca.model.libraryItem;

import com.NovaStack.biblioteca.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Monograph extends LibraryItem{
    @Column(nullable = false)
    private String institution;

    private String course;

    public Monograph(String name, String author, LocalDate releaseDate, User user, String institution, String course) {
        super(name, author, releaseDate, user);
        this.institution = institution;
        this.course = course;
    }

    public Monograph() {
    }

    @Override
    public String getType() {
        return "monograph";
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
