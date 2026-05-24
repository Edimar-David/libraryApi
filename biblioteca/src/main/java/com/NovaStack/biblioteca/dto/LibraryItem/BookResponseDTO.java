package com.NovaStack.biblioteca.dto.LibraryItem;

import com.NovaStack.biblioteca.model.enums.BookCategory;

import java.time.LocalDate;

public record BookResponseDTO(
        Long id,
        String name,
        String author,
        LocalDate releaseDate,
        Boolean isBorrowed,
        BookCategory category
) {
}
