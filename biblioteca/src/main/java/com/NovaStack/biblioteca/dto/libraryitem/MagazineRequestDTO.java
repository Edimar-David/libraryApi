package com.NovaStack.biblioteca.dto.libraryitem;

import java.time.LocalDate;

public record MagazineRequestDTO(
        String name,
        String author,
        LocalDate releaseDate,
        Integer editionNumber
) {
}
