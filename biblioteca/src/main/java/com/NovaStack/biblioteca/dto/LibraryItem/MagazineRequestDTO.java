package com.NovaStack.biblioteca.dto.LibraryItem;

import java.time.LocalDate;

public record MagazineRequestDTO(
        String name,
        String author,
        LocalDate releaseDate,
        Integer editionNumber
) {
}
