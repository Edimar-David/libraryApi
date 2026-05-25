package com.NovaStack.biblioteca.model.libraryItem;

import com.NovaStack.biblioteca.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Media extends LibraryItem {
    @Column(nullable = false)
    private String mediaFormat;

    private Integer durationTime;

    public Media(String name, String author, LocalDate releaseDate, User user, String mediaFormat, Integer durationTime) {
        super(name, author, releaseDate, user);
        this.mediaFormat = mediaFormat;
        this.durationTime = durationTime;
    }

    public Media() {
    }

    public String getMediaFormat() {
        return mediaFormat;
    }

    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public Integer getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(Integer durationTime) {
        this.durationTime = durationTime;
    }
}
