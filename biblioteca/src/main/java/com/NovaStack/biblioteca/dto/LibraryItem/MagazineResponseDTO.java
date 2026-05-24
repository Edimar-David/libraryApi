package com.NovaStack.biblioteca.dto.LibraryItem;

import com.NovaStack.biblioteca.model.enums.BookCategory;

import java.time.LocalDate;

public record MagazineResponseDTO(
        Long id,
        String name,
        String author,
        LocalDate releaseDate,
        Boolean isBorrowed,
        Integer editionNumber
) {
}
