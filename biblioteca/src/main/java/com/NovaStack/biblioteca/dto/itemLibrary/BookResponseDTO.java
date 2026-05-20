package com.NovaStack.biblioteca.dto.itemLibrary;

import com.NovaStack.biblioteca.model.enums.BookCategory;

import java.time.LocalDate;

public record BookResponseDTO(
        Long id,
        String name,
        String author,
        BookCategory category,
        LocalDate releaseDate,
        Boolean isBorrowed
) {
}
